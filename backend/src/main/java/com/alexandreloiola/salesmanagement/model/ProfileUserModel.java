package com.alexandreloiola.salesmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TB_PROFILE_USER")
public class ProfileUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profileType", length = 64, nullable = false)
    private String profileType;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private ProfileModel profileModel;
}
