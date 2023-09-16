package com.alexandreloiola.salesmanagement.rest.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class OrderStatusUpdateForm {
    @NotBlank(message = "O campo descrição não pode ficar em branco")
    @Size(min = 5, max = 100, message = "A descrição deve ter entre 5 e 100 caracteres")
    private String description;

    @NotNull(message = "O campo de status não pode estar vazio")
    private Boolean isActive;
}
