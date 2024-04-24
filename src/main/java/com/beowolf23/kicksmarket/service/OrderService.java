package com.beowolf23.kicksmarket.service;

import com.beowolf23.kicksmarket.exception.ProductNotFoundException;
import com.beowolf23.kicksmarket.model.dao.Order;
import com.beowolf23.kicksmarket.model.dao.Product;
import com.beowolf23.kicksmarket.model.dto.order.OrderItem;
import com.beowolf23.kicksmarket.model.dto.order.OrderRequest;
import com.beowolf23.kicksmarket.repository.OrderRepository;
import com.beowolf23.kicksmarket.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private ProductService productService;

    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public Order createOrder(OrderRequest orderRequest) throws ProductNotFoundException {

        var orderedProductQuantities = createProductQuantitiesMap(orderRequest);
        var availableProductQuantities = productService.getAvailableProductQuantities(orderedProductQuantities.keySet());

        if(!areProductsAvailable(orderedProductQuantities, availableProductQuantities)) {
            throw new ProductNotFoundException("One or more products are not available");
        }

        return Order.builder().build();
    }

    private Map<Long, Integer> createProductQuantitiesMap(OrderRequest orderRequest) {
        return Arrays.stream(orderRequest.getItems())
                .collect(Collectors.toMap(OrderItem::getProductId, OrderItem::getQuantity));
    }

    private boolean areProductsAvailable(Map<Long, Integer> orderedProductQuantities,
                                         List<Product> availableProducts) throws ProductNotFoundException {
        for(Map.Entry<Long, Integer> entry : orderedProductQuantities.entrySet()) {
            Long productId = entry.getKey();
            Integer requestedQuantity = entry.getValue();
            Product product = availableProducts
                    .stream()
                    .filter(p -> p.getProductId().equals(productId))
                    .findFirst()
                    .orElse(null);
            if(product == null || product.getQuantityAvailable() < requestedQuantity) {
                throw new ProductNotFoundException("One or more products are not available!");
            }
        }
        return true;
    }
}
