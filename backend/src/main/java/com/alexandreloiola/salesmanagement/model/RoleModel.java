package com.alexandreloiola.salesmanagement.model;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "TB_ROLE")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @ManyToMany(mappedBy = "roles")
    private Set<UserModel> users = new HashSet<>();

}
