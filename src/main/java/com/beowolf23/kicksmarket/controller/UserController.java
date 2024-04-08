package com.beowolf23.kicksmarket.controller;

import com.beowolf23.kicksmarket.model.dao.User;
import com.beowolf23.kicksmarket.service.UserService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String message = userService.addUserAccount(user);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return ResponseEntity.of(userService.getUser(userId));
    }
}
