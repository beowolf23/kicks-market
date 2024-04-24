package com.beowolf23.kicksmarket.model.dto.order;

import com.beowolf23.kicksmarket.model.dao.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private User user;
    private OrderItem[] items;
}
