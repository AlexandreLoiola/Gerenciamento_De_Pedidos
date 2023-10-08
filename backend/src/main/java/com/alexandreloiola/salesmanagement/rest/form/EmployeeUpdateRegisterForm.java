package com.alexandreloiola.salesmanagement.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeUpdateRegisterForm {

    @NotEmpty(message = "O campo nome não pode estar vazio")
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 256)
    private String name;

    @NotNull(message = "O campo data não pode ficar estar vazio.")
    @Past(message = "A data de nascimento informada deve ser anterior a data de hoje.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotNull(message = "O campo de status não pode estar vazio.")
    private Boolean isActive;

    @NotNull(message = "O campo data de demissão não pde ser nulo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate resignationDate;

    @NotNull(message = "O campo data de contratação não pode ser nulo.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate hireDate;

    @NotNull(message ="O campo de salário não pode ser nulo")
    @DecimalMax(value = "99999")
    @DecimalMin(value = "00.01")
    private BigDecimal salary;

    @NotEmpty(message = "O campo cargo não pode ficar ficar vazio" )
    @NotBlank(message = "O campo cargo não pode ficar em branco")
    private String position;

}
