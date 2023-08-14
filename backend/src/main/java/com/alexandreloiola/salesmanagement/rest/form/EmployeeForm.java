package com.alexandreloiola.salesmanagement.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeForm {

    @NotNull(message = "O campo identificador não pode ficar vazio")
    private Long id;

    @NotNull(message = "O campo data de contratação não pode ficar em branco.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate hireDate;

    @NotNull(message ="O campo de salário não pode ficar vazio")
    @DecimalMax(value = "99999")
    @DecimalMin(value = "00.01")
    private BigDecimal salary;

    @NotNull(message ="O campo de identificador de cargo não pode ficar vazio")
    @DecimalMax(value = "99999")
    @DecimalMin(value = "00.01")
    private String position;

}
