package com.example.bdbj.dto;

import com.example.bdbj.domain.OrderStatus;
import lombok.NonNull;

public class UpdateOrderReqDto {
    private final OrderStatus orderStatus;

    public UpdateOrderReqDto(@NonNull OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
