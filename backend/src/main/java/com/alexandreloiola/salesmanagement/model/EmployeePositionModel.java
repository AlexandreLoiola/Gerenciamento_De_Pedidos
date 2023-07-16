package com.alexandreloiola.salesmanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_EMPLOYEE_POSITION")
public class EmployeePositionModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 100, nullable = false, unique = true)
    private String description;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

}
