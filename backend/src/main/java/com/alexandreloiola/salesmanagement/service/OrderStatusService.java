package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.OrderStatusModel;
import com.alexandreloiola.salesmanagement.repository.OrderStatusRepository;
import com.alexandreloiola.salesmanagement.rest.dto.OrderStatusDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderStatusForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderStatusUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusAlreadyExistsException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusInsertException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusNotFoundException;
import com.alexandreloiola.salesmanagement.service.exceptions.orderStatus.OrderStatusUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public List<OrderStatusDto> getAllOrderStatus() {
        List<OrderStatusModel> orderStatusModelist = orderStatusRepository.findAll();
        return convertModelListToDtoList(orderStatusModelist);
    }

    private OrderStatusModel findOrderStatusByDescription(String orderDescription) {
        return orderStatusRepository.findByDescription(orderDescription)
                .orElseThrow(() -> new OrderStatusNotFoundException(
                        String.format("O status '%s' não foi encontrado", orderDescription))
                );
    }

    public OrderStatusDto getOrderStatusByDescription(String orderDescription) {
        OrderStatusModel orderStatusModel = findOrderStatusByDescription(orderDescription);
        return convertModelToDto(orderStatusModel);
    }

    @Transactional
    public OrderStatusDto insertOrderStatus(OrderStatusForm orderStatusForm) {
        if (orderStatusRepository.findByDescription(orderStatusForm.getDescription()).isPresent()) {
            throw new OrderStatusAlreadyExistsException(
                    String.format("O status '%s' já está cadastrado", orderStatusForm.getDescription())
            );
        }
        try {
            OrderStatusModel orderStatusModel = convertFormToModel(orderStatusForm);
            orderStatusModel.setIsActive(true);
            orderStatusModel = orderStatusRepository.save(orderStatusModel);
            return convertModelToDto(orderStatusModel);
        } catch (DataIntegrityViolationException err) {
            throw new OrderStatusInsertException(
                    String.format("Falha ao cadastrar o status '%s'. Verifique os dados.", orderStatusForm.getDescription())
            );
        }
    }

    @Transactional
    public OrderStatusDto updateOrderStatus(String orderStatusDescription, OrderStatusUpdateForm orderStatusUpdateForm) {
        OrderStatusModel orderStatusModel = findOrderStatusByDescription(orderStatusDescription);
        try {
            OrderStatusModel orderStatusUpdate = orderStatusModel;
            orderStatusUpdate.setDescription(orderStatusUpdateForm.getDescription());
            orderStatusUpdate.setIsActive(orderStatusUpdateForm.getIsActive());
            orderStatusUpdate = orderStatusRepository.save(orderStatusUpdate);
            return convertModelToDto(orderStatusUpdate);
        } catch (DataIntegrityViolationException err) {
            throw new OrderStatusUpdateException(
                    String.format("Falha ao atualizar o produto %s. Verifique se os dados estão corretos", orderStatusUpdateForm.getDescription())
            );
        }
    }

    @Transactional
    public void deleteOrderStatus(String orderDescription) {
        OrderStatusModel orderStatusModel = findOrderStatusByDescription(orderDescription);
        Long orderStatusId = orderStatusModel.getId();
        orderStatusRepository.deleteById(orderStatusId);
    }

    public OrderStatusModel convertFormToModel(OrderStatusForm orderStatusForm) {
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        orderStatusModel.setDescription(orderStatusForm.getDescription());
        return orderStatusModel;
    }

    public OrderStatusDto convertModelToDto(OrderStatusModel orderStatusModel) {
        OrderStatusDto orderStatusDto = new OrderStatusDto();
        orderStatusDto.setDescription(orderStatusModel.getDescription());
        orderStatusDto.setIsActive(orderStatusModel.getIsActive());
        return orderStatusDto;
    }

    public List<OrderStatusDto> convertModelListToDtoList(List<OrderStatusModel> list) {
        List<OrderStatusDto> orderStatusDtoList = new ArrayList<>();
        for (OrderStatusModel orderStatusList : list) {
            OrderStatusDto orderStatusDto = this.convertModelToDto(orderStatusList);
            orderStatusDtoList.add(orderStatusDto);
        }
        return orderStatusDtoList;
    }
}
