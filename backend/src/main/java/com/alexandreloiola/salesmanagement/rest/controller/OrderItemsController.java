package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.OrderDto;
import com.alexandreloiola.salesmanagement.rest.dto.OrderItemsDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderItemsForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderItemsUpdateForm;
import com.alexandreloiola.salesmanagement.service.OrderItemsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping
    public ResponseEntity<List<OrderItemsDto>> getAllOrdersItems() {
        List<OrderItemsDto> orderItemsDtoList = orderItemsService.getAllOrderItems();
        return ResponseEntity.ok().body(orderItemsDtoList);
    };

    @GetMapping("/{orderNumber}")
    public ResponseEntity<List<OrderItemsDto>> getOrderItemsById (
            @PathVariable ("orderNumber") Long orderNumber
    ) {
        List<OrderItemsDto> orderItemsDtoList = orderItemsService.getOrderItems(orderNumber);
        return ResponseEntity.ok().body(orderItemsDtoList);
    };

    @PostMapping
    public ResponseEntity<OrderItemsDto> insertOrderItem (
            @Valid @RequestBody OrderItemsForm orderItemsForm
    ) {
        OrderItemsDto orderItemsDto = orderItemsService.insertOrderItems(orderItemsForm);
        return ResponseEntity.ok().body(orderItemsDto);
    };

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemsDto> updateOrderItem(
            @PathVariable ("id") Long id,
            @Valid @RequestBody OrderItemsUpdateForm orderItemsUpdateForm
    ) {
        OrderItemsDto orderItemsDto = orderItemsService.updateOrderDto(id, orderItemsUpdateForm);
        return ResponseEntity.ok().body(orderItemsDto);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(
            @PathVariable("id") Long id
    ) {
        orderItemsService.deleteOrderItems(id);
        return ResponseEntity.noContent().build();
    };
}
