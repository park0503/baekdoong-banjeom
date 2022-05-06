package com.example.bdbj.controller;

import com.example.bdbj.domain.Order;
import com.example.bdbj.domain.OrderStatus;
import com.example.bdbj.dto.OrderResDto;
import com.example.bdbj.dto.UpdateOrderReqDto;
import com.example.bdbj.service.OrderService;
import com.example.bdbj.util.GlobalUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public String showOrders(Model model) {
        model.addAttribute(
                "orders",
                orderService.getAllOrders().
                        stream().
                        map(OrderResDto::new).
                        collect(Collectors.toList()));
        return "orders/orders";
    }

    @GetMapping("/{id}")
    public String showCreateOrderPage(@PathVariable String id, Model model) {
        UUID orderId = GlobalUtils.convertStringToUUID(id);
        model.addAttribute(
                "order",
                new OrderResDto(orderService.getOrderById(orderId))
        );
        return "orders/show";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateOrderPage(@PathVariable String id, Model model) {
        UUID orderId = GlobalUtils.convertStringToUUID(id);
        Order order = orderService.getOrderById(orderId);
        model.addAttribute(
                "orderId",
                order.getOrderId()
        );
        model.addAttribute(
                "orderStatuses",
                OrderStatus.values()
        );
        model.addAttribute(
                "currentStatus",
                order.getOrderStatus()
        );
        return "orders/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateOrder(@PathVariable String id, UpdateOrderReqDto updateOrderReqDto) {
        UUID orderId = GlobalUtils.convertStringToUUID(id);
        Order order = orderService.getOrderById(orderId);
        order.setOrderStatus(updateOrderReqDto.getOrderStatus());
        orderService.updateOrder(order);
        return "redirect:/orders/" + id;
    }
}
