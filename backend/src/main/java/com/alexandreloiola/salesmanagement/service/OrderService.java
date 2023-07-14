package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.*;
import com.alexandreloiola.salesmanagement.repository.*;
import com.alexandreloiola.salesmanagement.rest.dto.OrderDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderDto> getAllOrders() {
        updateOrderPrice();
        List<OrderModel> orderModelList = orderRepository.findAll();
        return convertListToDto(orderModelList);
    }

    public OrderDto getOrderById(Long id) {
        try {
            updateOrderPrice(id);
            OrderModel orderModel = orderRepository.findById(id).get();
            return convertModelToDto(orderModel);
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("Pedido não encontrado!");
        }
    }

    @Transactional
    public OrderDto insertOrder(OrderForm orderForm) {
        try {
            OrderModel newOrder = convertFormToModel(orderForm);
            newOrder.setPrice(new BigDecimal(0.00));
            newOrder.setOrderNumber(generateOrderNumber());
            newOrder.setDataTime(LocalDateTime.now());
            newOrder = orderRepository.save(newOrder);

            return convertModelToDto(newOrder);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Campo(s) obrigatório(s) do pedido não foi(foram) devidamente preenchido(s)."
            );
        }
    }

    @Transactional
    public OrderDto updateOrder(Long id, OrderUpdateForm orderUpdateForm) {
        updateOrderPrice();
        try {
            Optional<OrderModel> orderModel = orderRepository.findById(id);
            if (orderModel.isPresent()) {
                OrderModel orderUpdated = orderModel.get();
                orderUpdated.setStatus(orderUpdateForm.getStatus());

                orderRepository.save(orderUpdated);
                return convertModelToDto(orderUpdated);
            } else {
                throw new DataIntegrityViolationException("O pedido não pode ser atualizado");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Campo(s) obrigatório(s) do pedido não foi(foram) devidamente preenchido(s)."
            );
        }
    }

    @Transactional
    private void updateOrderPrice(long orderId) {
        try {
            List<OrderItemsModel> orderItemsModelList = orderItemsRepository.findAllByOrderId(orderId).get();

            BigDecimal price = new BigDecimal(0);
            for (OrderItemsModel orderItem : orderItemsModelList) {
                price = price.add(
                        orderItem.getProduct().getUnitPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                );
            }
            orderRepository.findById(orderId).get().setPrice(price);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Não foi possível pegar o preço total da ordem de pedido"
            );
        }
    }

    @Transactional
    private void updateOrderPrice() {
        try {
            List<OrderModel> orders = orderRepository.findAll();
            for (OrderModel order : orders) {
                List<OrderItemsModel> orderItemsModelList = orderItemsRepository.findAllByOrderId(order.getId()).get();

                BigDecimal price = new BigDecimal(0);
                for (OrderItemsModel orderItem : orderItemsModelList) {
                    price = price.add(
                            orderItem.getProduct().getUnitPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                    );
                }
                order.setPrice(price);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Não foi possível pegar o preço total das ordem(ns) de(os) pedido(s)"
            );
        }
    }

    @Transactional
    public void deleteOrder(Long id) {
        try {
            if (orderRepository.existsById(id)) {
                orderRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o pedido");
        }
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
        OrderModel orderModel = new OrderModel();

        try {
            ProfileModel profileSellerModel = profileRepository.findByName(orderForm.getSeller()).get();
            orderModel.setSeller(profileSellerModel);
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("O vendedor não foi encontrado");
        }
        try {
            ProfileModel profileCustomerModel = profileRepository.findByName(orderForm.getCustomer()).get();
            orderModel.setCustomer(profileCustomerModel);
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("O cliente não foi encontrado");
        }

        orderModel.setStatus(orderForm.getStatus());

        return orderModel;
    }

    private OrderDto convertModelToDto(OrderModel orderModel) {
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderNumber(orderModel.getOrderNumber());
        orderDto.setPrice(orderModel.getPrice());
        orderDto.setDateTime(orderModel.getDataTime());
        orderDto.setStatus(orderModel.getStatus());
        orderDto.setCustomer(orderModel.getCustomer().getName());
        orderDto.setSeller(orderModel.getSeller().getName());

        return orderDto;
    }

    private List<OrderDto> convertListToDto(List<OrderModel> list) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderModel orderModel : list) {
            OrderDto orderDto = this.convertModelToDto(orderModel);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}
