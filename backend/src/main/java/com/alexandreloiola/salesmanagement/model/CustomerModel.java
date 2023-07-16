package com.alexandreloiola.salesmanagement.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Data
@Table(name="TB_CUSTOMER")
public class CustomerModel{

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private PersonModel person;

}
