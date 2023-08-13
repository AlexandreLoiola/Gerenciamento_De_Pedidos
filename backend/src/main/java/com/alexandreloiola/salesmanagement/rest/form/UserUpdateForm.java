package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateForm {

    @NotNull(message = "O campo ativo não pode ser nulo")
    private Boolean isActive;

}
