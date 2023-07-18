package com.alexandreloiola.salesmanagement.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
public class CustomerForm {

    @NotNull(message = "O campo identificador não pode ficar vazio")
    private Long id;

    @NotNull(message = "O campo data não pode ficar em branco.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate resignationDate;

}
