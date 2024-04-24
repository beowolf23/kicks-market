package com.beowolf23.kicksmarket.controller;
import com.beowolf23.kicksmarket.exception.DatabaseInsertionException;
import com.beowolf23.kicksmarket.model.dao.Address;
import com.beowolf23.kicksmarket.model.dto.ApiResponse;
import com.beowolf23.kicksmarket.model.dto.address.AddressDTO;
import com.beowolf23.kicksmarket.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addresses")
    @Operation(summary = "Add an address")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Address added successfully",
            content = @Content(schema = @Schema(implementation = Address.class)))
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "422", description = "Unprocessable entity")
    public ResponseEntity<ApiResponse<Address>> addAddress(@RequestBody AddressDTO address)
            throws DatabaseInsertionException {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(address));
    }
}
