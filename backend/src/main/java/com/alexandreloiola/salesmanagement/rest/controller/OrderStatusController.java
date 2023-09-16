package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.OrderStatusDto;
import com.alexandreloiola.salesmanagement.rest.dto.ProductDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderStatusForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderStatusUpdateForm;
import com.alexandreloiola.salesmanagement.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/orderStatus")
public class OrderStatusController {
    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping
    public ResponseEntity<List<OrderStatusDto>> getAllOrderStatus() {
        List<OrderStatusDto> orderStatusDtoList = orderStatusService.getAllOrderStatus();
        return ResponseEntity.ok().body(orderStatusDtoList);
    }

    @GetMapping("/{orderStatusDescription}")
    public ResponseEntity<OrderStatusDto> getOrderStatusById(
            @PathVariable("orderStatusDescription") String orderDescription
    ) {
        OrderStatusDto orderStatusDto = orderStatusService.getOrderStatusByDescription(orderDescription);
        return ResponseEntity.ok().body(orderStatusDto);
    }

    @PostMapping
    public ResponseEntity<OrderStatusDto> insertOrderStatus(
            @Valid @RequestBody OrderStatusForm orderStatusForm
    ) {
        OrderStatusDto orderStatusDto = orderStatusService.insertOrderStatus(orderStatusForm);
        return ResponseEntity.ok().body(orderStatusDto);
    }

    @PutMapping("/{orderStatusDescription}")
    public ResponseEntity<OrderStatusDto> updateOrderStatus(
            @PathVariable("orderStatusDescription") String orderStatusDescription,
            @Valid @RequestBody OrderStatusUpdateForm orderStatusUpdateForm
    ) {
        OrderStatusDto orderStatusDto = orderStatusService.updateOrderStatus(orderStatusDescription, orderStatusUpdateForm);
        return ResponseEntity.ok().body(orderStatusDto);
    }

    @DeleteMapping("/{orderStatusDescription}")
    public ResponseEntity<OrderStatusDto> deleteOrderStatus(
            @PathVariable("orderStatusDescription") String orderStatusDescription
    ) {
        orderStatusService.deleteOrderStatus(orderStatusDescription);
        return ResponseEntity.noContent().build();
    }
}
