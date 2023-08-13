package com.alexandreloiola.salesmanagement.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class CustomerUpdateRegisterForm {

    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 256)
    private String name;

    @NotNull(message = "O campo data não pode ficar em branco.")
    @Past(message = "A data de nascimento informada deve ser anterior a data de hoje.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotNull(message = "O campo de status não pode estar vazio")
    private Boolean isActive;

    @NotNull(message = "O campo data de cadastro não pode ficar em branco.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;

}
