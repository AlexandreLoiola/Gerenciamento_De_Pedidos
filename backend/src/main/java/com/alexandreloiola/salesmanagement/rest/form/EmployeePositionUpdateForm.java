package com.alexandreloiola.salesmanagement.rest.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EmployeePositionUpdateForm {
    @NotEmpty(message = "O campo descrição não pode ser vazio")
    @NotBlank(message = "O campo descrição nao pode ficar em branco")
    private String description;

    @NotNull(message = "O campo de status não pode ser nulo")
    private Boolean isActive;
}
