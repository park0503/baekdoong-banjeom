package com.example.bdbj.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Order {
    private final UUID orderId;
    private final String phoneNumber;
    private final Address address;
    private List<OrderItem> orderItems;
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

    public void setOrderStatus(@NonNull OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        updateUpdatedAt();
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        updateUpdatedAt();
    }

    public void updateUpdatedAt() {
        this.updatedAt = LocalDateTime.now().withNano(0);
    }
}
