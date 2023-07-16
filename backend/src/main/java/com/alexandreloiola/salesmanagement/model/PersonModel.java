package com.alexandreloiola.salesmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="TB_PERSON")
public class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "email", length = 200, nullable = false, unique = true)
    private String email;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @OneToOne(mappedBy = "person")
    private UserModel user;

    @OneToOne(mappedBy = "person")
    private CustomerModel customer;

    @OneToOne(mappedBy = "person")
    private EmployeeModel employee;

}
