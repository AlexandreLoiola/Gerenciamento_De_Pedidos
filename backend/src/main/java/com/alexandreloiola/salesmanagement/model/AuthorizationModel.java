package com.alexandreloiola.salesmanagement.model;

import lombok.Data;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "TB_AUTHORIZATION")
public class AuthorizationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @ManyToMany
    @JoinTable(
            name = "Role_Authorization",
            joinColumns = @JoinColumn(name = "id_authorization"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<RoleModel> roles = new HashSet<>();
}
