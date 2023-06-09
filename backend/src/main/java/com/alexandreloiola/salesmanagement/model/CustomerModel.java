package com.alexandreloiola.salesmanagement.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_CUSTOMER")
public class CustomerModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 256, nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "isActive",  nullable = false)
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "customerModel")
    private UserModel userModel;

}
