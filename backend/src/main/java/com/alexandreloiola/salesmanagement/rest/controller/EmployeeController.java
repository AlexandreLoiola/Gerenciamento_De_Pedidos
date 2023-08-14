package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.EmployeeDto;
import com.alexandreloiola.salesmanagement.rest.form.EmployeeForm;
import com.alexandreloiola.salesmanagement.rest.form.EmployeeUpdateForm;
import com.alexandreloiola.salesmanagement.rest.form.EmployeeRegistrationForm;
import com.alexandreloiola.salesmanagement.rest.form.EmployeeUpdateRegisterForm;
import com.alexandreloiola.salesmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(employeeDtoList);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<EmployeeDto> getEmployeeByCpf(@PathVariable("cpf") String cpf) {
        EmployeeDto employeeDto = employeeService.getEmployeeByCpf(cpf);
        return ResponseEntity.ok().body(employeeDto);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> insertEmployee(@Valid @RequestBody EmployeeRegistrationForm employeeRegistrationForm) {
        EmployeeDto employeeDto = employeeService.registerEmployee(employeeRegistrationForm);
        return ResponseEntity.ok().body(employeeDto);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<EmployeeDto> updateRegisterEmployee(
            @PathVariable("cpf") String cpf,
            @Valid @RequestBody EmployeeUpdateRegisterForm employeeUpdateRegisterForm
    ) {
        EmployeeDto employeeDto = employeeService.updateRegisterEmployee(cpf, employeeUpdateRegisterForm);
        return ResponseEntity.ok().body(employeeDto);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("cpf") String cpf) {
        employeeService.deleteEmployee(cpf);
        return ResponseEntity.noContent().build();
    }
}
