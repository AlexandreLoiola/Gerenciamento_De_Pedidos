package com.alexandreloiola.salesmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_CLIENT")
public class CustomerModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 256, nullable = false)
    private String name;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    @Column(name = "isActive",  nullable = false)
    private Boolean isActive;

}
