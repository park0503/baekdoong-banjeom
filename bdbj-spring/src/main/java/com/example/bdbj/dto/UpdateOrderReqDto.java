package com.example.bdbj.dto;

import com.example.bdbj.domain.OrderStatus;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class UpdateOrderReqDto {
    private final OrderStatus orderStatus;

    public UpdateOrderReqDto(@NonNull OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
