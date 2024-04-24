package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.exception.DatabaseInsertionException;
import com.beowolf23.kicksmarket.model.dao.Address;
import com.beowolf23.kicksmarket.model.dao.User;
import com.beowolf23.kicksmarket.model.dto.ApiResponse;
import com.beowolf23.kicksmarket.model.dto.address.AddressDTO;
import com.beowolf23.kicksmarket.repository.AddressRepository;
import com.beowolf23.kicksmarket.utils.ValidationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final Validator validator;

    public AddressService(AddressRepository addressRepository, Validator validator) {
        this.addressRepository = addressRepository;
        this.validator = validator;
    }

    public ApiResponse<Address> addAddress(AddressDTO addressDTO) throws DatabaseInsertionException {
        Address address = Address.builder()
                .postalCode(addressDTO.getPostalCode())
                .country(addressDTO.getCountry())
                .county(addressDTO.getCounty())
                .street(addressDTO.getStreet())
                .build();

        ValidationUtils.validate(address);

        try {
            address = addressRepository.save(address);
            return ApiResponse.<Address>builder()
                    .data(address)
                    .statusCode(HttpStatus.CREATED.value())
                    .success(true)
                    .build();
        } catch (Exception exception) {
            throw new DatabaseInsertionException("Address insertion failed: " + exception.getMessage());
        }
    }
}
