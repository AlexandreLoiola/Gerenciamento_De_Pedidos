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

@RestController
@RequestMapping("/employeePosition")
public class EmployeePositionController {
    @Autowired
    private EmployeePositionService employeePositionService;

    @GetMapping
    public ResponseEntity<List<EmployeePositionDto>> getAllEmployeePositions() {
        List<EmployeePositionDto> employeePositionDtoList = employeePositionService.getAllEmployeePosition();
        return ResponseEntity.ok().body(employeePositionDtoList);
    }

    @GetMapping("/{description}")
    public ResponseEntity<EmployeePositionDto> getEmployeePositionById(
            @PathVariable("description") String description
    ) {
        EmployeePositionDto employeePositionDto = employeePositionService.getEmployeePositionByDescription(description);
        return ResponseEntity.ok().body(employeePositionDto);
    }

    @PostMapping
    public ResponseEntity<EmployeePositionDto> insertEmployeePosition(
            @Valid @RequestBody EmployeePositionForm employeePositionForm
            ) {
        EmployeePositionDto employeePositionDto = employeePositionService.insertEmployeePosition(employeePositionForm);
        return ResponseEntity.ok().body(employeePositionDto);
    }

    @PutMapping("/{description}")
    public ResponseEntity<EmployeePositionDto> updateEmployeePosition(
            @PathVariable("description") String description,
            @Valid @RequestBody EmployeePositionUpdateForm employeePositionUpdateForm
    ) {
        EmployeePositionDto employeePositionDto = employeePositionService.updateEmployeePosition(description, employeePositionUpdateForm);
        return ResponseEntity.ok().body(employeePositionDto);
    }

    @DeleteMapping("/{description}")
    public ResponseEntity<EmployeePositionDto> deleteEmployeePosition(
            @PathVariable("description") String description
    ) {
        employeePositionService.deleteEmployeePosition(description);
        return ResponseEntity.noContent().build();
    }
}
