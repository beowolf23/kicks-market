package com.beowolf23.kicksmarket.model.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "street")
    @NotNull(message = "You must specify the street")
    @NotEmpty(message = "Street must not be empty")
    private String street;

    @Column(name = "county")
    @NotNull(message = "You must specify the county")
    @NotEmpty(message = "County must not be empty")
    private String county;

    @Column(name = "country")
    @NotNull(message = "You must specify the country")
    @NotEmpty(message = "Country must not be empty")
    private String country;

    @Column(name = "postal_code")
    @NotNull(message = "You must specify the postal code")
    private Integer postalCode;
}
