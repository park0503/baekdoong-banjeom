package com.example.bdbj.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class OrderItem {
    private final UUID orderId;
    private final UUID menuId;
    private final Category category;
    private final Integer price;
    private final Integer quantity;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public OrderItem(UUID orderId, @NonNull UUID menuId, @NonNull Category category, @NonNull Integer price, Integer quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.category = category;
        this.price = price;
        this.quantity = quantity != null ? quantity : 1;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now().withNano(0);
        this.updatedAt = updatedAt != null ? updatedAt : null;
    }
}
