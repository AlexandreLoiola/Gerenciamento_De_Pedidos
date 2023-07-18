package com.alexandreloiola.salesmanagement.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_CUSTOMER")
public class CustomerModel{

    @Id
    private Long id;

    @Column(name = "resignation_date", nullable = false)
    private LocalDate resignationDate;
}
