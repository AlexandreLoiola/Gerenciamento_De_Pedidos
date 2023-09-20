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
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.order.OrderNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderItems.OrderItemDeletionException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderItems.OrderItemsInsertException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderItems.OrderItemsNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderItems.OrderItemsUpdateException;
import com.alexandreloiola.salesmanagement.service.exceptions.product.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    private OrderModel findOrderModelByOrderNumber(Long orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("O pedido de número '%s' não foi encontrado", orderNumber))
                );
    }

    private ProductModel findProductModelByName(String productName) {
        return productRepository.findByName(productName)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("O produto de nome '%s' não foi encontrado", productName)
                ));
    }

    public List<OrderItemsDto> getOrderItems(Long orderNumber) {
        OrderModel orderModel = findOrderModelByOrderNumber(orderNumber);

        List<OrderItemsModel> orderItemsModelList = orderItemsRepository.findAllByOrderId(orderModel.getId())
                .orElseThrow(() -> new OrderItemsNotFoundException(
                        String.format("O(s) item(ns) do pedido '%s' não foi(foram) encontrado(s)!", orderNumber))
                );
        return convertListModelToDto(orderItemsModelList);
    }

    @Transactional
    public OrderItemsDto insertOrderItems(OrderItemsForm orderItemsForm) {
        Long orderId = findOrderModelByOrderNumber(orderItemsForm.getOrderNumber()).getId();
        Long productId = findProductModelByName(orderItemsForm.getProductName()).getId();
        try {
            if (orderItemsRepository.findByOrderIdAndProductId(orderId, productId).isPresent()) {
                OrderItemsModel orderItemsModel = orderItemsRepository
                        .findByOrderIdAndProductId(orderId, productId)
                        .orElseThrow(() -> new OrderNotFoundException(
                                String.format("O item do pedido '%s' não foi encontrado", orderItemsForm.getOrderNumber())
                        ));
                orderItemsModel.setQuantity(orderItemsModel.getQuantity() + orderItemsForm.getQuantity());
                orderItemsModel = orderItemsRepository.save(orderItemsModel);
                return convertModelToDto(orderItemsModel);
            } else {
                OrderItemsModel newOrderItemsModel = convertFormToModel(orderItemsForm);
                newOrderItemsModel = orderItemsRepository.save(newOrderItemsModel);
                return convertModelToDto(newOrderItemsModel);
            }
        } catch (DataIntegrityViolationException err) {
            throw new OrderItemsInsertException(
                    String.format("Não foi possível salvar a item no pedido '%s. Verifique os seus dados", orderItemsForm.getOrderNumber())
            );
        }
    }

    @Transactional
    public OrderItemsDto updateOrderItems(Long orderNumber, String productName, OrderItemsUpdateForm orderItemsUpdateForm) {
        Long orderId = findOrderModelByOrderNumber(orderNumber).getId();
        Long productId = findProductModelByName(productName).getId();
        OrderItemsModel orderItemsUpdated = orderItemsRepository.findByOrderIdAndProductId(orderId, productId)
                .orElseThrow(() -> new OrderItemsNotFoundException("Não foi possível encontrar o item de pedido"));
        try {
            orderItemsUpdated.setQuantity(orderItemsUpdateForm.getQuantity());
            orderItemsUpdated = orderItemsRepository.save(orderItemsUpdated);
            return convertModelToDto(orderItemsUpdated);
        } catch (DataIntegrityViolationException err) {
            throw new OrderItemsUpdateException(
                    String.format("Não foi possível salvar a item no pedido '%s. Verifique os seus dados", orderNumber)
            );
        }
    }

    @Transactional
    public void deleteOrderItems(long orderId) {
        List<OrderItemsModel> orderItemsModelList = orderItemsRepository.findAllByOrderId(orderId)
                .orElseThrow(() -> new OrderItemsNotFoundException(
                        String.format("O(s) item(ns) do pedido '%s' não foi(foram) encontrado(s)!"))
                );
        for (OrderItemsModel orderItem : orderItemsModelList) {
            try {
                orderItemsRepository.deleteById(orderItem.getId());
            } catch (DataAccessException e) {
                throw new OrderItemDeletionException(
                        String.format("Não foi possível deletar os itens do pedido '%s'", orderItem.getOrder())
                );
            }
        }
    }

    private OrderItemsModel convertFormToModel(OrderItemsForm orderItemsForm) {
        OrderModel orderModel = findOrderModelByOrderNumber(orderItemsForm.getOrderNumber());
        ProductModel productModel = findProductModelByName(orderItemsForm.getProductName());
        OrderItemsModel orderItemsModel = new OrderItemsModel();
        orderItemsModel.setOrder(orderModel);
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