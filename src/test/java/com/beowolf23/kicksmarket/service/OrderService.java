package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.model.dto.order.OrderItem;
import com.beowolf23.kicksmarket.model.dto.order.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class OrderService {

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();

}
