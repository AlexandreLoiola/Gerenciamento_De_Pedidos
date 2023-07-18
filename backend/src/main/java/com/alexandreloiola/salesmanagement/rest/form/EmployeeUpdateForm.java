package com.alexandreloiola.salesmanagement.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeUpdateForm {

    @NotNull(message = "O campo data de contratação não pode ficar em branco.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate hire_date;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate resignation_date;

    @NotNull(message ="O campo de salário não pode ficar vazio")
    @DecimalMax(value = "99999")
    @DecimalMin(value = "00.01")
    private BigDecimal salary;

    @NotNull(message ="O campo de identificador de cargo não pode ficar vazio")
    @DecimalMax(value = "99999")
    @DecimalMin(value = "00.01")
    private Long idPosition;

}
