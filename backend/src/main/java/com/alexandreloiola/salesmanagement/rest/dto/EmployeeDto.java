package com.alexandreloiola.salesmanagement.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private PersonDto personDto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate hireDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate resignationDate;

    private BigDecimal salary;

    private EmployeePositionDto position;

}
