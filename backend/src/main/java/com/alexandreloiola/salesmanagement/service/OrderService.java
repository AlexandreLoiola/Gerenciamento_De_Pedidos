package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import com.alexandreloiola.salesmanagement.model.OrderModel;
import com.alexandreloiola.salesmanagement.model.SellerModel;
import com.alexandreloiola.salesmanagement.repository.CustomerRepository;
import com.alexandreloiola.salesmanagement.repository.OrderRepository;
import com.alexandreloiola.salesmanagement.repository.SellerRepository;
import com.alexandreloiola.salesmanagement.rest.dto.OrderDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SellerRepository sellerRepository;

    public List<OrderDto> getAllOrders() {
        List<OrderModel> orderModelList = orderRepository.findAll();
        return convertListToDto(orderModelList);
    }

    public OrderDto getOrderById(Long id) {
        try {
            OrderModel orderModel = orderRepository.findById(id).get();
            return convertModelToDto(orderModel);
        } catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Pedido não encontrado!");
        }
    }

    public OrderDto insertOrder(OrderForm orderForm) {
        try {
            OrderModel newOrder = convertFormToModel(orderForm);
            newOrder.setDataTime(LocalDateTime.now());
            newOrder = orderRepository.save(newOrder);

            return convertModelToDto(newOrder);
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do pedido não foi(foram) devidamente preenchido(s).");
        }
    }

    public OrderDto updateOrder(Long id, OrderUpdateForm orderUpdateForm) {
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
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do pedido não foi(foram) devidamente preenchido(s).");
        }
    }

    public void deleteOrder(Long id) {
        try {
            if (orderRepository.existsById(id)) {
                orderRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o pedido");
        }
    }

    private OrderModel convertFormToModel(OrderForm orderForm) {
        OrderModel orderModel = new OrderModel();

        try {
            SellerModel sellerModel = sellerRepository.findByName(orderForm.getSeller()).get();
            orderModel.setSeller(sellerModel);
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("O vendedor não foi encontrado");
        }
        try {
            CustomerModel customerModel = customerRepository.findByName(orderForm.getCustomer()).get();
            orderModel.setCustomer(customerModel);
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("O cliente não foi encontrado");
        }

        orderModel.setOrderNumber(orderForm.getOrderNumber());
        orderModel.setPrice(orderForm.getPrice());
        orderModel.setStatus(orderForm.getStatus());

        return orderModel;
    }

    private OrderDto convertModelToDto(OrderModel orderModel) {
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderNumber(orderModel.getId());
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
