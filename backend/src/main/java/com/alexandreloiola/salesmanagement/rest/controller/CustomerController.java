package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.form.CustomerRegisterForm;
import com.alexandreloiola.salesmanagement.service.CustomerService;
import com.alexandreloiola.salesmanagement.rest.dto.CustomerDto;
import com.alexandreloiola.salesmanagement.rest.form.CustomerForm;
import com.alexandreloiola.salesmanagement.rest.form.CustomerUpdateForm;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(
            @Valid @RequestBody CustomerRegisterForm registerCustomerForm
            ) {
        CustomerDto customerDto = customerService.registerCustomer(registerCustomerForm);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return ResponseEntity.ok().body(customerDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") long id){
        CustomerDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> insertCustomer(@Valid @RequestBody CustomerForm customerForm) {
        CustomerDto customerDto = customerService.insertCustomer(customerForm);
        return ResponseEntity.ok().body(customerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @Valid @RequestBody CustomerUpdateForm customerUpdateForm,
            @PathVariable("id") long id
    ) {
        CustomerDto customerDto = customerService.updateCustomer(id, customerUpdateForm);
        return ResponseEntity.ok().body(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
