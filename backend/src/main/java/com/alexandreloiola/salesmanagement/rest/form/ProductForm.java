package com.alexandreloiola.salesmanagement.rest.form;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Data
public class ProductForm {

    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 128)
    private String name;

    @NotEmpty
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 256)
    private String description;

    @NotNull
    @DecimalMax(value = "99999.99")
    @Digits(integer = 16, fraction = 2)
    @DecimalMin(value = "0.00")
    private BigDecimal unitPrice;

    @NotNull
    @BooleanFlag
    private Boolean isActive;

}
