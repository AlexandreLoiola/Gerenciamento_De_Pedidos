package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class SellerForm {

    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 256)
    private String name;

    @NotNull(message = "O campo data não pode ficar em branco.")
    @Past(message = "A data de nascimento informada deve ser anterior a data de hoje.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotEmpty
    @NotBlank(message = "O campo CPF não pode ficar em branco")
    @CPF(message = "O CPF informado não é válido")
    @Size(min = 11, max = 11)
    private String cpf;
}
