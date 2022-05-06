package com.example.bdbj.service;

import com.example.bdbj.domain.Address;
import com.example.bdbj.domain.Order;
import com.example.bdbj.domain.OrderItem;
import com.example.bdbj.domain.OrderStatus;
import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.RecordNotFoundException;
import com.example.bdbj.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order insertOrder(Order order) {
        return  orderRepository.save(order);
    }

    public Order createOrder(String phoneNumber, Address address, List<OrderItem> orderItems) {
        return orderRepository.save(
                new Order(
                        UUID.randomUUID(),
                        phoneNumber,
                        address,
                        orderItems,
                        OrderStatus.ORDERED
                )
        );
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(UUID orderId) {
        return orderRepository.
                findById(orderId).
                orElseThrow(
                        () -> new RecordNotFoundException(
                                "해당 아이디의 order가 없습니다.",
                                ErrorCode.ORDER_NOT_FOUND
                        )
                );
    }

    public List<Order> getOrdersByPhoneNumber(String phoneNumber) {
        return orderRepository.findByPhoneNumber(phoneNumber);
    }

    public Order updateOrder(Order order) {
        return orderRepository.update(order);
    }

    public void removeAllOrder() {
        orderRepository.deleteAll();
    }
}
