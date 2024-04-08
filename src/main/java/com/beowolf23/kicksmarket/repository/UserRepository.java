package com.beowolf23.kicksmarket.repository;

import com.beowolf23.kicksmarket.model.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
