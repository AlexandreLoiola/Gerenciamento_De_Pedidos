package com.alexandreloiola.salesmanagement.rest.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionsDto implements Serializable {
    private static final long serialVersionUID=1L;
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
