package com.beowolf23.kicksmarket.model.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "street")
    @NotNull
    private String street;

    @Column(name = "county")
    @NotNull
    private String county;

    @Column(name = "country")
    @NotNull
    private String country;

    @Column(name = "postal_code")
    @NotNull
    private Integer postalCode;

    @OneToOne(mappedBy = "address")
    private User user;
}
