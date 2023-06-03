package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.OrderItemsModel;
import com.alexandreloiola.salesmanagement.model.OrderModel;
import com.alexandreloiola.salesmanagement.model.ProductModel;
import com.alexandreloiola.salesmanagement.repository.OrderItemsRepository;
import com.alexandreloiola.salesmanagement.repository.OrderRepository;
import com.alexandreloiola.salesmanagement.repository.ProductRepository;
import com.alexandreloiola.salesmanagement.rest.dto.OrderItemsDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderItemsForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderItemsUpdateForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderUpdateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrderItemsDto> getAllOrderItems() {
        List<OrderItemsModel> orderItemsModelList = orderItemsRepository.findAll();
        return convertListModelToDto(orderItemsModelList);
    }

    public OrderItemsDto getOrderItems(Long id) {
        OrderItemsModel orderItemsModel = orderItemsRepository.findById(id).get();
        return convertModelToDto(orderItemsModel);
    }

    public OrderItemsDto insertOrderItems(OrderItemsForm orderItemsForm) {
        OrderItemsModel newOrderItemsModel = convertFormToModel(orderItemsForm);
        newOrderItemsModel = orderItemsRepository.save(newOrderItemsModel);

        return convertModelToDto(newOrderItemsModel);
    }

    public OrderItemsDto updateOrderDto(Long id, OrderItemsUpdateForm orderItemsUpdateForm) {
        Optional<OrderItemsModel> orderItemsModel = orderItemsRepository.findById(id);
        if (orderItemsModel.isPresent()) {
            OrderItemsModel orderItemsUpdated = orderItemsModel.get();
            orderItemsUpdated.setQuantity(orderItemsUpdateForm.getQuantity());
            orderItemsUpdated = orderItemsRepository.save(orderItemsUpdated);

            return convertModelToDto(orderItemsUpdated);
        } else {
            throw new DataIntegrityViolationException("Os itens do pedido não pode ser atualizado");
        }
    }

    public void deleteOrderItems(Long id) {
        if (orderItemsRepository.existsById(id)) {
            orderItemsRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Os itens do pedido não pode ser deletado");
        }
    }

    private OrderItemsModel convertFormToModel(OrderItemsForm orderItemsForm) {
        OrderItemsModel orderItemsModel = new OrderItemsModel();

        OrderModel orderModel = orderRepository.findByOrderNumber(orderItemsForm.getOrderNumber()).get();
        orderItemsModel.setOrder(orderModel);

        ProductModel productModel = productRepository.findByName(orderItemsForm.getProductName()).get();
        orderItemsModel.setProduct(productModel);

        orderItemsModel.setQuantity(orderItemsForm.getQuantity());

        return orderItemsModel;
    }

    private OrderItemsDto convertModelToDto(OrderItemsModel orderItemsModel) {
        OrderItemsDto orderItemsDto = new OrderItemsDto();
        orderItemsDto.setQuantity(orderItemsModel.getQuantity());
        orderItemsDto.setOrderNumber(orderItemsModel.getOrder().getOrderNumber());
        orderItemsDto.setProductName(orderItemsModel.getProduct().getName());

        return orderItemsDto;
    }

    private List<OrderItemsDto> convertListModelToDto(List<OrderItemsModel> list) {
        List<OrderItemsDto> orderItemsDtoList = new ArrayList<>();
        for (OrderItemsModel orderItemsModel : list) {
            OrderItemsDto orderItemsDto = this.convertModelToDto(orderItemsModel);
            orderItemsDtoList.add(orderItemsDto);
        }

        return orderItemsDtoList;
    }
}
