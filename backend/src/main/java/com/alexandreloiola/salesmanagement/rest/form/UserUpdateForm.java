package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateForm {

    @NotEmpty
    @NotBlank(message = "Senha da Pessoa deve ser preenchida.")
    @Size(min = 5, max = 50, message = "Senha do Pessoa deve ter entre 8 e 50 caracteres.")
    private String password;

    @NotNull(message = "O campo ativo não pode ser nulo")
    private Boolean isActive;
}
