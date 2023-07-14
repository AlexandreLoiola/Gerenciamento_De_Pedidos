package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
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
    @NotBlank(message = "O campo descrição não pode ficar em branco.")
    @Size(max = 256)
    private String description;

    @NotNull
    @DecimalMax(value = "99999.99")
    @Digits(integer = 16, fraction = 2)
    @DecimalMin(value = "0.01")
    private BigDecimal unitPrice;

    @NotNull(message ="O campo de quantidade não pode ficar vazio")
    @DecimalMax(value = "99999")
    @DecimalMin(value = "-99999")
    private Integer stockQuantity;

    @NotNull
    @BooleanFlag
    private Boolean isActive;

}
