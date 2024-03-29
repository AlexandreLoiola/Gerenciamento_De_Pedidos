package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.form.CustomerRegisterForm;
import com.alexandreloiola.salesmanagement.service.CustomerService;
import com.alexandreloiola.salesmanagement.rest.dto.CustomerDto;
import com.alexandreloiola.salesmanagement.rest.form.CustomerForm;
import com.alexandreloiola.salesmanagement.rest.form.CustomerUpdateRegisterForm;

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
            @Valid @RequestBody CustomerRegisterForm customerRegisterForm
    ) {
        CustomerDto customerDto = customerService.registerCustomer(customerRegisterForm);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return ResponseEntity.ok().body(customerDtoList);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerDto> getCustomerById(
            @PathVariable("cpf") String cpf
    ) {
        CustomerDto customerDto = customerService.getCustomerByCpf(cpf);
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> insertCustomer(
            @Valid @RequestBody CustomerForm customerForm
    ) {
        CustomerDto customerDto = customerService.insertCustomer(customerForm);
        return ResponseEntity.ok().body(customerDto);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @Valid @RequestBody CustomerUpdateRegisterForm customerUpdateForm,
            @PathVariable("cpf") String cpf
    ) {
        CustomerDto customerDto = customerService.updateRegisterCustomer(cpf, customerUpdateForm);
        return ResponseEntity.ok().body(customerDto);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("cpf") String cpf
    ) {
        customerService.deleteCustomer(cpf);
        return ResponseEntity.noContent().build();
    }
}
