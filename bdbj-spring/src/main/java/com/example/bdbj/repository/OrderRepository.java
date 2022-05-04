package com.example.bdbj.repository;

import com.example.bdbj.domain.Order;
import com.example.bdbj.domain.OrderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);

    List<Order> findAll();

    List<OrderItem> findOrderItemsByOrderId(UUID orderId);

    Optional<Order> findById(UUID menuId);

    List<Order> findByPhoneNumber(String phoneNumber);

    Order update(Order menu);

    void deleteAll();
}
