package com.alexandreloiola.salesmanagement.model;

import javax.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "TB_USER")
public class UserModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 256, nullable = false)
    private String email;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private CustomerModel customerModel;
}
