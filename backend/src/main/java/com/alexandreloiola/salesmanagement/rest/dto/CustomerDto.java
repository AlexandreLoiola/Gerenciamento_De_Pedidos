package com.alexandreloiola.salesmanagement.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private PersonDto personDto;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;

}