package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.OrderDto;
import com.alexandreloiola.salesmanagement.rest.form.OrderForm;
import com.alexandreloiola.salesmanagement.rest.form.OrderUpdateForm;
import com.alexandreloiola.salesmanagement.service.OrderService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        return ResponseEntity.ok().body(orderDtoList);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderNumber") long orderNumber){
        OrderDto orderDto = orderService.getOrderByOrderNumber(orderNumber);
        return ResponseEntity.ok().body(orderDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> insertOrder(@Valid @RequestBody OrderForm orderForm) {
        OrderDto orderDto = orderService.insertOrder(orderForm);
        return ResponseEntity.ok().body(orderDto);
    }

    @PutMapping("/{orderNumber}")
    public ResponseEntity<OrderDto> updateOrder(
            @Valid @RequestBody OrderUpdateForm orderUpdateForm,
            @PathVariable("orderNumber") long orderNumber
    ) {
        OrderDto orderDto = orderService.updateOrder(orderNumber, orderUpdateForm);
        return ResponseEntity.ok().body(orderDto);
    }

    @DeleteMapping("/{orderNumber}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderNumber") long orderNumber) {
        orderService.deleteOrder(orderNumber);
        return ResponseEntity.noContent().build();
    }
}
