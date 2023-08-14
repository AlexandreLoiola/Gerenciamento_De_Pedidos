package com.alexandreloiola.salesmanagement.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeRegistrationForm {

    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 256)
    private String name;

    @NotEmpty
    @NotBlank(message = "O campo email não pode ficar em branco.")
    @Size(max = 256)
    private String email;

    @NotNull(message = "O campo data não pode ficar em branco.")
    @Past(message = "A data de nascimento informada deve ser anterior a data de hoje.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotEmpty
    @NotBlank(message = "O campo CPF não pode ficar em branco")
    @CPF(message = "O CPF informado é inválido")
    @Size(min = 11, max = 11)
    private String cpf;

    @NotEmpty
    @NotBlank(message = "O campo senha não pode ficar vazio")
    @Size(min = 8, max = 50, message = "Senha do Pessoa deve ter entre 8 e 50 caracteres.")
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate hireDate;

    @NotNull(message ="O campo de salário não pode ficar vazio")
    @DecimalMax(value = "99999")
    @DecimalMin(value = "00.01")
    private BigDecimal salary;


    @NotEmpty
    @NotBlank(message = "O campo cargo não pode ficar vazio")
    private String position;

}
