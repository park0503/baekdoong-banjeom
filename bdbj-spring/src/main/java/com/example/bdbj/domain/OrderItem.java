package com.example.bdbj.domain;

import com.example.bdbj.util.GlobalUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderItem {
    private final UUID orderId;
    private final UUID menuId;
    private final String menuName;
    private final Category category;
    private final Integer price;
    private final Integer quantity;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderItem(UUID orderId, UUID menuId, String menuName, Category category, Integer price, Integer quantity, LocalDateTime createdAt, LocalDateTime updatedAt) {
        GlobalUtils.checkMenuNull(menuId, menuName, category, price);
        this.orderId = orderId;
        this.menuId = menuId;
        this.menuName = menuName;
        this.category = category;
        this.price = price;
        this.quantity = quantity != null ? quantity : 1;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now().withNano(0);
        this.updatedAt = updatedAt;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getMenuId() {
        return menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
