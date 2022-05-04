package com.example.bdbj.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class OrderItem {
    private final UUID orderId;
    private final UUID menuId;
    private final Category category;
    private Integer price;
    private Integer quantity;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public OrderItem(@NonNull UUID orderId, @NonNull UUID menuId, @NonNull Category category, @NonNull Integer price) {
        this.orderId = orderId;
        this.menuId = menuId;
        this.category = category;
        this.price = price;
        this.quantity = 1;
        this.createdAt = LocalDateTime.now().withNano(0);
    }
}
