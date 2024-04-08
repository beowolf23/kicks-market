package com.beowolf23.kicksmarket.repository;

import com.beowolf23.kicksmarket.model.dao.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
