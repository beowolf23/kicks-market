package com.beowolf23.kicksmarket.controller;

import com.beowolf23.kicksmarket.model.dao.Address;
import com.beowolf23.kicksmarket.model.dao.User;
import com.beowolf23.kicksmarket.model.dto.ApiResponse;
import com.beowolf23.kicksmarket.model.dto.user.UserResponse;
import com.beowolf23.kicksmarket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/users/{userId}/address")
    public ResponseEntity<ApiResponse<UserResponse>> assignAddressToUser(@PathVariable Long userId,
                                                 @RequestBody Address address) {
        return ResponseEntity.ok(userService.updateUserAddress(userId, address));
    }
}
