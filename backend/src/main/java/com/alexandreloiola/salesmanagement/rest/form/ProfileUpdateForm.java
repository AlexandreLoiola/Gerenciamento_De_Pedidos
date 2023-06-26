package com.alexandreloiola.salesmanagement.rest.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.BooleanFlag;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ProfileUpdateForm {
    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 100)
    private String name;

    @NotEmpty
    @NotBlank(message = "O campo email não pode ficar em branco.")
    @Size(max = 256)
    private String email;

    @NotNull(message = "O campo data não pode ficar em branco.")
    @Past(message = "A data de nascimento informada deve ser anterior a data de hoje")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotNull
    @BooleanFlag
    private Boolean isActive;
}
