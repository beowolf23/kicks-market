package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.model.dao.User;
import com.beowolf23.kicksmarket.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;

    private Validator validator;

    public UserService(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
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

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }
}
