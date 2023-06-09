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

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") long id){
        OrderDto orderDto = orderService.getOrderById(id);
        return ResponseEntity.ok().body(orderDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> insertOrder(@Valid @RequestBody OrderForm orderForm) {
        OrderDto orderDto = orderService.insertOrder(orderForm);
        return ResponseEntity.ok().body(orderDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateorder(
            @Valid @RequestBody OrderUpdateForm orderUpdateForm,
            @PathVariable("id") long id
    ) {
        OrderDto orderDto = orderService.updateOrder(id, orderUpdateForm);
        return ResponseEntity.ok().body(orderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteorder(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
