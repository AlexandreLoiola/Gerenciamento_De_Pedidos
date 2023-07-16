package com.alexandreloiola.salesmanagement.rest.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthorizationForm {
    @NotNull(message = "O campo descrição não pode estar vazio")
    private String description;
}
