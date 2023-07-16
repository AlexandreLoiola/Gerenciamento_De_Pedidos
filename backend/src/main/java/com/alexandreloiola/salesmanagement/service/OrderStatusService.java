package com.alexandreloiola.salesmanagement.service;

import com.alexandreloiola.salesmanagement.model.OrderStatusModel;
import com.alexandreloiola.salesmanagement.repository.OrderStatusRepository;
import com.alexandreloiola.salesmanagement.rest.dto.OrderStatusDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderStatusForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderStatusUpdateForm;
import com.alexandreloiola.salesmanagement.service.exceptions.DataIntegrityException;
import com.alexandreloiola.salesmanagement.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderStatusService {
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public List<OrderStatusDto> getAllOrderStatus() {
        List<OrderStatusModel> orderStatusModelist = orderStatusRepository.findAll();
        return convertModelListToDtoList(orderStatusModelist);
    }

    public OrderStatusDto getOrderStatusById(Long id) {
        try {
            OrderStatusModel orderStatusModel = orderStatusRepository.findById(id).get();
            return convertModelToDto(orderStatusModel);
        } catch(NoSuchElementException err) {
            throw new ObjectNotFoundException("Status de pedido não encontrado!");
        }
    }

    public OrderStatusDto insertOrderStatus(OrderStatusForm orderStatusForm) {
        try {
            Optional<OrderStatusModel> byDescription = orderStatusRepository.findByDescription(orderStatusForm.getDescription());
            if (byDescription.isPresent()) {
                throw new DataIntegrityException("Essa descrição já foi cadastrado");
            }
            OrderStatusModel orderStatusModel = convertFormToModel(orderStatusForm);
            orderStatusModel.setIsActive(true);
            orderStatusModel = orderStatusRepository.save(orderStatusModel);

            return convertModelToDto(orderStatusModel);
        }  catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do Status de Pedido não foi(foram) devidamente preenchido(s).");
        }
    }

    public OrderStatusDto updateOrderStatus(Long id, OrderStatusUpdateForm orderStatusUpdateForm) {
        try {
            Optional<OrderStatusModel> orderStatusModel = orderStatusRepository.findById(id);
            if (orderStatusModel.isPresent()) {
                OrderStatusModel orderStatusUpdate = orderStatusModel.get();
                orderStatusUpdate.setDescription(orderStatusUpdateForm.getDescription());
                orderStatusUpdate.setIsActive(orderStatusUpdateForm.getIsActive());
                orderStatusUpdate = orderStatusRepository.save(orderStatusUpdate);
                return convertModelToDto(orderStatusUpdate);
            }  else {
                throw new DataIntegrityViolationException("Campo(s) obrigatório(s) do produto não foi(foram) devidamente preenchido(s).");
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Esse produto não está cadastrado");
        }
    }

    public void deleteOrderStatus(Long id) {
        try {
            if (orderStatusRepository.existsById(id)) {
                orderStatusRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException err) {
            throw new DataIntegrityViolationException("Não foi possível deletar o Status do Pedido");
        }
    }

    public OrderStatusModel convertFormToModel(OrderStatusForm orderStatusForm) {
        OrderStatusModel orderStatusModel = new OrderStatusModel();

        orderStatusModel.setDescription(orderStatusForm.getDescription());

        return orderStatusModel;
    }

    public OrderStatusDto convertModelToDto(OrderStatusModel orderStatusModel) {
        OrderStatusDto orderStatusDto = new OrderStatusDto();

        orderStatusDto.setId(orderStatusModel.getId());
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
