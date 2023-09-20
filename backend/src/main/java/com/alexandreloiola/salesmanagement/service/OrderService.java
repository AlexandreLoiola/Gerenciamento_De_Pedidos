package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.*;
import com.alexandreloiola.salesmanagement.repository.*;
import com.alexandreloiola.salesmanagement.rest.dto.OrderDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.order.*;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.person.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;
    private final OrderStatusService orderStatusService;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderItemsRepository orderItemsRepository;

    private final OrderItemsService orderItemsService;
    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, PersonRepository personRepository,
                        OrderStatusService orderStatusService, OrderStatusRepository orderStatusRepository,
                        OrderItemsRepository orderItemsRepository, OrderItemsService orderItemsService,
                        ProductRepository productRepository, CustomerRepository customerRepository,
                        EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
        this.orderStatusService = orderStatusService;
        this.orderStatusRepository = orderStatusRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.orderItemsService = orderItemsService;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<OrderDto> getAllOrders() {
        List<OrderModel> orderModelList = orderRepository.findAll();
        return convertListToDto(orderModelList);
    }

    private OrderModel findOrderModelByOrderNumber(Long orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("O pedido de número '%s' não foi encontrado", orderNumber))
                );
    }

    public OrderDto getOrderByOrderNumber(Long orderNumber) {
        OrderModel orderModel = findOrderModelByOrderNumber(orderNumber);
        return convertModelToDto(orderModel);
    }

    @Transactional
    public OrderDto insertOrder(OrderForm orderForm) {
        OrderModel newOrder = convertFormToModel(orderForm);
        newOrder.setIdStatus(findOrderStatusModel("Em processamento"));
        newOrder.setOrderNumber(generateOrderNumber());
        newOrder.setTotalPrice(new BigDecimal(0.00));
        newOrder.setDataTime(LocalDateTime.now());
        try {
            newOrder = orderRepository.save(newOrder);
            return convertModelToDto(newOrder);
        } catch (DataIntegrityViolationException err) {
            throw new OrderInsertException("Falha ao cadastrar o pedido. Verifique os dados informados");
        }
    }

    private OrderStatusModel findOrderStatusModel(String status) {
        return orderStatusRepository.findByDescription(status)
                .orElseThrow(() -> new OrderStatusNotFoundException(
                        String.format("O status '%s' não foi encontrado", status)
                ));
    }

    @Transactional
    public OrderDto updateOrder(Long orderNumber, OrderUpdateForm orderUpdateForm) {
        OrderModel orderUpdated = findOrderModelByOrderNumber(orderNumber);
        OrderStatusModel orderStatusModel = findOrderStatusModel(orderUpdateForm.getStatus());
        try {
            orderUpdated.setIdStatus(orderStatusModel);
            orderRepository.save(orderUpdated);
            return convertModelToDto(orderUpdated);
        } catch (DataIntegrityViolationException err) {
            throw new OrderUpdateException(
                    String.format("Falha ao atualizar o pedido '%s'. Verifique se os dados estão corretos", orderNumber)
            );
        }
    }

    @Transactional
    public void deleteOrder(Long orderNumber) {
        OrderModel orderModel = findOrderModelByOrderNumber(orderNumber);
        Long id = orderModel.getId();
        orderItemsService.deleteOrderItems(id);
        orderRepository.deleteById(id);
    }

    @Transactional
    private void updateOrderPrice(long orderId) {
        List<OrderItemsModel> orderItemsModelList = orderItemsRepository.findAllByOrderId(orderId)
                .orElseThrow(() -> new OrderPriceUpdateException("Não foi possível pegar o preço total da ordem de pedido"));
        BigDecimal price = calculateTotalPrice(orderItemsModelList);
        orderRepository.findById(orderId).get().setTotalPrice(price);
    }

    @Transactional
    private void updateOrderPrice() {
        List<OrderModel> orders = orderRepository.findAll();
        for (OrderModel order : orders) {
            List<OrderItemsModel> orderItemsModelList = orderItemsRepository.findAllByOrderId(order.getId())
                    .orElseThrow(() -> new OrderPriceUpdateException(
                            String.format("Não foi possível encontrar itens para o pedido de ID: %d", order.getId())
                    ));
            BigDecimal price = calculateTotalPrice(orderItemsModelList);
            order.setTotalPrice(price);
        }
    }

    private BigDecimal calculateTotalPrice(List<OrderItemsModel> items) {
        BigDecimal price = new BigDecimal(0);
        for (OrderItemsModel item : items) {
            price = price.add(item.getProduct().getUnitPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return price;
    }

    private long generateOrderNumber() {
        LocalDate date = LocalDate.now();
        String dateString = String.format("%02d%02d%02d", date.getYear() % 100, date.getMonthValue(), date.getDayOfMonth());
        long orderNumber;
        do {
            long randomNum = ThreadLocalRandom.current().nextLong(1_000_000_000L);
            orderNumber = Long.parseLong(dateString + String.format("%09d", randomNum));
        } while (orderRepository.findByOrderNumber(orderNumber).isPresent());
        return orderNumber;
    }

    private OrderModel convertFormToModel(OrderForm orderForm) {
        PersonModel customer = personRepository.findByCpf(orderForm.getCpfCustomer())
                .orElseThrow(() -> new PersonNotFoundException(
                        String.format("O cpf '%s' não foi encontrado", orderForm.getCpfCustomer()))
                );
        customerRepository.findById(customer.getId()).orElseThrow(
                () -> new NotValidCustomerException(String.format("O cpf '%s' não é de um cliente", orderForm.getCpfCustomer()))
        );
        PersonModel seller = personRepository.findByCpf(orderForm.getCpfSeller())
                .orElseThrow(() -> new PersonNotFoundException(
                        String.format("Vendedor com cpf '%s' não foi encontrado", orderForm.getCpfSeller()))
                );
        EmployeeModel employeeModel = employeeRepository.findById(seller.getId()).orElseThrow(
                () -> new NotValidSellerException(
                        String.format("O cpf '%s' não é de um vendedor", orderForm.getCpfSeller())
        ));
        if (employeeModel.getEmployeePosition().getDescription() != "Vendedor") {
            String.format("O cpf '%s' não é de um vendedor", orderForm.getCpfSeller());
        }
        OrderModel orderModel = new OrderModel();
        orderModel.setIdCustomer(customer);
        orderModel.setIdSeller(seller);
        return orderModel;
    }

    private OrderDto convertModelToDto(OrderModel orderModel) {
        OrderDto orderDto = new OrderDto();
        updateOrderPrice(orderModel.getId());
        orderDto.setOrderNumber(orderModel.getOrderNumber());
        orderDto.setPrice(orderModel.getTotalPrice());
        orderDto.setDateTime(orderModel.getDataTime());
        orderDto.setStatus(orderModel.getIdStatus().getDescription());
        orderDto.setCustomer(orderModel.getIdCustomer().getName());
        orderDto.setSeller(orderModel.getIdSeller().getName());
        return orderDto;
    }

    private List<OrderDto> convertListToDto(List<OrderModel> list) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderModel orderModel : list) {
            OrderDto orderDto = this.convertModelToDto(orderModel);
            orderDtoList.add(orderDto);
        }
        updateOrderPrice();
        return orderDtoList;
    }
}