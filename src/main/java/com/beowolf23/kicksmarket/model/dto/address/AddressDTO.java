package com.beowolf23.kicksmarket.model.dto.address;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressDTO {

    private String street;
    private String county;
    private String country;
    private Integer postalCode;
}
