package com.example.bdbj.domain;

import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.FieldBlankException;
import com.example.bdbj.util.GlobalUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final String phoneNumber;
    private final Address address;
    private final List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, String phoneNumber, Address address, List<OrderItem> orderItems, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        checkOrder(orderId, phoneNumber, address, orderItems);
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus != null ? orderStatus : OrderStatus.ORDERED;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now().withNano(0);
        this.updatedAt = updatedAt;
    }

    public Order(UUID orderId, String phoneNumber, Address address, List<OrderItem> orderItems, OrderStatus orderStatus) {
        checkOrder(orderId, phoneNumber, address, orderItems);
        this.orderId = orderId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus != null ? orderStatus : OrderStatus.ORDERED;
        this.createdAt = LocalDateTime.now().withNano(0);
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

    public void setOrderStatus(OrderStatus orderStatus) {
        GlobalUtils.checkNull(orderStatus);
        this.orderStatus = orderStatus;
        updateUpdatedAt();
    }

    public void updateUpdatedAt() {
        this.updatedAt = LocalDateTime.now().withNano(0);
    }

    private void checkOrder(UUID orderId, String phoneNumber, Address address, List<OrderItem> orderItems) {
        if (orderId == null || phoneNumber == null || address == null || orderItems == null) {
            throw new FieldBlankException("입력값이 부족합니다.", ErrorCode.FIELD_BLANK);
        }
    }
}
