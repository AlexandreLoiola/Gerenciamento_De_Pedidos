package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import lombok.Data;

@Data
public class UserForm {

    @NotEmpty
    @NotBlank(message = "O campo email não pode ficar vazio")
    private String email;

    @NotEmpty
    @NotBlank(message = "O campo senha não pode ficar vazio")
    @Size(min = 8, max = 50, message = "Senha do Pessoa deve ter entre 8 e 50 caracteres.")
    private String password;

}
