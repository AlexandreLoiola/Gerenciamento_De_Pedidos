package com.alexandreloiola.salesmanagement.model;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "TB_EMPLOYEE")
public class EmployeeModel {

    @Id
    private Long id;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "resignation_date")
    private LocalDate resignationDate;

    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @OneToOne
    @JoinColumn(name = "id_position", referencedColumnName = "id")
    private EmployeePositionModel employeePosition;

}

