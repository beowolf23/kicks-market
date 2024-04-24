package com.beowolf23.kicksmarket.model.dto.user;

import com.beowolf23.kicksmarket.model.dao.Address;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {

    private Long userId;
    private String email;
    private String username;
    private Address address;
}
