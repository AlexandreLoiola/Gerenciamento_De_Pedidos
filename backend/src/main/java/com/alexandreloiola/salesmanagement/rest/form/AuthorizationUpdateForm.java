package com.alexandreloiola.salesmanagement.rest.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthorizationUpdateForm {
    @NotNull(message = "O campo descrição não pode estar vazio")
    private String description;

    @NotNull(message = "O campo de status não pode estar vazio")
    private Boolean isActive;
}
