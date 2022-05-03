package com.example.bdbj.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Order {
    private final UUID orderId;
    private final String phoneNumber;
    private final Address address;
    private List<OrderItem> orderItems = new ArrayList<>();
    private OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Order(@NonNull UUID orderId, @NonNull String phoneNumber, @NonNull Address address) {
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createdAt = LocalDateTime.now().withNano(0);
        this.orderStatus = OrderStatus.CART;
    }
}
