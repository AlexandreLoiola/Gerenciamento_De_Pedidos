package com.alexandreloiola.salesmanagement.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TB_USER")
public class UserModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 256, nullable = false)
    private String email;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "userModel")
    private ProfileUserModel profileUser;
}
