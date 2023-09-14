package com.alexandreloiola.salesmanagement.rest.form;

import javax.validation.constraints.*;
import jdk.jfr.BooleanFlag;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateForm {

    @NotEmpty(message = "O campo nome não pode ser vazio")
    @NotBlank(message = "O campo nome não pode ficar em branco.")
    @Size(max = 100)
    private String name;

    @NotEmpty(message = "O campo descrição não pode ser vazio")
    @NotBlank(message = "O campo descrição não pode ficar em branco.")
    @Size(max = 200)
    private String description;

    @NotNull(message = "O campo preço unitário não pode ser vazio")
    @DecimalMax(value = "99999.99")
    @Digits(integer = 16, fraction = 2)
    @DecimalMin(value = "0.01")
    private BigDecimal unitPrice;

    @NotNull(message ="O campo de quantidade não pode ficar vazio")
    @DecimalMax(value = "99999")
    @DecimalMin(value = "0")
    private Integer stockQuantity;

    @NotNull(message = "O campo status não pode ficar vazio")
    @BooleanFlag
    private Boolean isActive;

}
