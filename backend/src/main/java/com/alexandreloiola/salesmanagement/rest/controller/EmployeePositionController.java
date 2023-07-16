package com.alexandreloiola.salesmanagement.rest.controller;

import com.alexandreloiola.salesmanagement.rest.dto.EmployeePositionDto;
import com.alexandreloiola.salesmanagement.rest.form.EmployeePositionForm;
import com.alexandreloiola.salesmanagement.rest.form.EmployeePositionUpdateForm;
import com.alexandreloiola.salesmanagement.service.EmployeePositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employeePosition")
public class EmployeePositionController {
    @Autowired
    private EmployeePositionService employeePositionService;

    @GetMapping
    public ResponseEntity<List<EmployeePositionDto>> getAllEmployeePositions() {
        List<EmployeePositionDto> employeePositionDtoList = employeePositionService.getAllEmployeePosition();
        return ResponseEntity.ok().body(employeePositionDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeePositionDto> getEmployeePositionById(
            @PathVariable("id") Long id
    ) {
        EmployeePositionDto employeePositionDto = employeePositionService.getEmployeePositionById(id);
        return ResponseEntity.ok().body(employeePositionDto);
    }

    @PostMapping
    public ResponseEntity<EmployeePositionDto> insertEmployeePosition(
            @Valid @RequestBody EmployeePositionForm employeePositionForm
            ) {
        EmployeePositionDto employeePositionDto = employeePositionService.insertEmployeePosition(employeePositionForm);
        return ResponseEntity.ok().body(employeePositionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeePositionDto> updateEmployeePosition(
            @PathVariable("id") Long id,
            @Valid @RequestBody EmployeePositionUpdateForm employeePositionUpdateForm
    ) {
        EmployeePositionDto employeePositionDto = employeePositionService.updateEmployeePosition(id, employeePositionUpdateForm);
        return ResponseEntity.ok().body(employeePositionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeePositionDto> deleteEmployeePosition(
            @PathVariable("id") Long id
    ) {
        employeePositionService.deleteEmployeePosition(id);
        return ResponseEntity.noContent().build();
    }
}
