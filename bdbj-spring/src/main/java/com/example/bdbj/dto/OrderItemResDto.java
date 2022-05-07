package com.example.bdbj.dto;

import com.example.bdbj.domain.Category;
import com.example.bdbj.domain.OrderItem;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderItemResDto {
    private final UUID menuId;
    private final String menuName;
    private final Category category;
    private final Integer price;
    private final Integer quantity;
    private final LocalDateTime createdAt;

    public OrderItemResDto(OrderItem entity) {
        this.menuId = entity.getMenuId();
        this.menuName = entity.getMenuName();
        this.category = entity.getCategory();
        this.price = entity.getPrice();
        this.quantity = entity.getQuantity();
        this.createdAt = entity.getCreatedAt();
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
}
