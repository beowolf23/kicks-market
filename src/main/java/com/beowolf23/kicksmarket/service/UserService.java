package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.model.dao.Address;
import com.beowolf23.kicksmarket.model.dao.User;
import com.beowolf23.kicksmarket.model.dto.ApiResponse;
import com.beowolf23.kicksmarket.model.dto.user.UserResponse;
import com.beowolf23.kicksmarket.repository.AddressRepository;
import com.beowolf23.kicksmarket.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;


    private AddressRepository addressRepository;

    private Validator validator;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, Validator validator) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.validator = validator;
    }

    public String addUserAccount(User user) {

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<User> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
        userRepository.save(user);
        return "Account for " + user.getEmail() + " created!";
    }

    public ApiResponse<UserResponse> getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ApiResponse.<UserResponse>builder()
                .data(
                        UserResponse.builder()
                                .userId(user.getUserId())
                                .email(user.getEmail())
                                .username(user.getUsername())
                                .address(user.getAddress())
                                .build()
                )
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }

    public ApiResponse<UserResponse> updateUserAddress(Long userId, Address address) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var addedAddress = addressRepository.save(address);
        user.setAddress(addedAddress);
        user = userRepository.save(user);
        return ApiResponse.<UserResponse>builder()
                .data(UserResponse.builder()
                        .userId(user.getUserId())
                        .address(addedAddress)
                        .email(user.getEmail())
                        .username(user.getUsername())
                        .build())
                .statusCode(HttpStatus.OK.value())
                .success(true)
                .build();
    }
}
