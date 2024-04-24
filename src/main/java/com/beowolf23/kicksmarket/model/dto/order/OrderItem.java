package com.beowolf23.kicksmarket.model.dto.order;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Long productId;
    private int quantity;
}
