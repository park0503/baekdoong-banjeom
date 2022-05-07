package com.example.bdbj.dto;

import com.example.bdbj.domain.Address;
import com.example.bdbj.domain.Order;
import com.example.bdbj.domain.OrderItem;
import com.example.bdbj.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderResDto {
    private final UUID orderId;
    private final String phoneNumber;
    private final Address address;
    private final List<OrderItem> orderItems;
    private final OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public OrderResDto(Order entity) {
        this.orderId = entity.getOrderId();
        this.phoneNumber = entity.getPhoneNumber();
        this.address = entity.getAddress();
        this.orderItems = entity.getOrderItems();
        this.orderStatus = entity.getOrderStatus();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
