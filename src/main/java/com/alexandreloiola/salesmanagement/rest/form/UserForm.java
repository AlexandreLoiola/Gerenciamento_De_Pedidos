package com.alexandreloiola.salesmanagement.rest.form;

import com.alexandreloiola.salesmanagement.model.CustomerModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar vazio")
    private String name;

    @NotEmpty
    @NotBlank(message = "O campo senha não pode ficar vazio")
    @Size(min = 8, max = 50, message = "Senha do Pessoa deve ter entre 8 e 50 caracteres.")
    private String password;

}
