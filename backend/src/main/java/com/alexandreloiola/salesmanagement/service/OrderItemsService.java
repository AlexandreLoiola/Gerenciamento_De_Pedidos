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
import org.springframework.beans.factory.annotation.Autowired;
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

    public OrderItemsDto getOrderItems(Long id) {
        try {
            OrderItemsModel orderItemsModel = orderItemsRepository.findById(id).get();
            return convertModelToDto(orderItemsModel);
        } catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("O(s) item(ns) do pedido não foi(foram) encontrado(s)!");
        }
    }

    @Transactional
    public OrderItemsDto insertOrderItems(OrderItemsForm orderItemsForm) {
        try {
            long orderId = orderRepository
                    .findByOrderNumber(orderItemsForm.getOrderNumber()).get().getId();
            long productId = productRepository
                    .findByName(orderItemsForm.getProductName()).get().getId();

            if (orderItemsRepository.findByOrderIdAndProductId(orderId, productId).isPresent()) {
                OrderItemsModel orderItemsModel = orderItemsRepository.findByOrderIdAndProductId(orderId, productId).get();

                orderItemsModel.setQuantity(
                        orderItemsModel.getQuantity() + orderItemsForm.getQuantity()
                );

                orderItemsModel = orderItemsRepository.save(orderItemsModel);
                return convertModelToDto(orderItemsModel);

            } else {
                OrderItemsModel newOrderItemsModel = convertFormToModel(orderItemsForm);

                newOrderItemsModel = orderItemsRepository.save(newOrderItemsModel);
                return convertModelToDto(newOrderItemsModel);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Campo(s) obrigatório(s) do(s) item(ns) do pedido não foi(foram) devidamente preenchido(s)."
            );
        }
    };

    @Transactional
    public OrderItemsDto updateOrderDto(Long id, OrderItemsUpdateForm orderItemsUpdateForm) {
        try {
            Optional<OrderItemsModel> orderItemsModel = orderItemsRepository.findById(id);
            if (orderItemsModel.isPresent()) {
                OrderItemsModel orderItemsUpdated = orderItemsModel.get();
                orderItemsUpdated.setQuantity(orderItemsUpdateForm.getQuantity());
                orderItemsUpdated = orderItemsRepository.save(orderItemsUpdated);

                return convertModelToDto(orderItemsUpdated);
            } else {
                throw new DataIntegrityViolationException("O(s) item(ns) do pedido não pode(podem) ser atualizado(s)");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException(
                    "Campo(s) obrigatório(s) do(s) item(ns) do pedido não foi(foram) devidamente preenchido(s)."
            );
        }
    };

    @Transactional
    public void deleteOrderItems(Long id) {
        try {
            if (orderItemsRepository.existsById(id)) {
                orderItemsRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o(s) item(ns) do pedido");
        }
    }

    private OrderItemsModel convertFormToModel(OrderItemsForm orderItemsForm) {
        OrderItemsModel orderItemsModel = new OrderItemsModel();
        try {
            OrderModel orderModel = orderRepository.findByOrderNumber(orderItemsForm.getOrderNumber()).get();
            orderItemsModel.setOrder(orderModel);
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("O pedido não foi encontrado");
        }
        try {
            ProductModel productModel = productRepository.findByName(orderItemsForm.getProductName()).get();
            orderItemsModel.setProduct(productModel);
        } catch (NoSuchElementException err) {
            throw new ObjectNotFoundException("O produto não foi encontrado");
        }

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
