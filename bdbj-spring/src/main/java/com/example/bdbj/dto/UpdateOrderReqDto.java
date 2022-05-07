package com.example.bdbj.dto;

import com.example.bdbj.domain.OrderStatus;
import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.FieldBlankException;

public class UpdateOrderReqDto {
    private final OrderStatus orderStatus;

    public UpdateOrderReqDto(OrderStatus orderStatus) {
        if (orderStatus == null) {
            throw new FieldBlankException("입력값이 부족합니다.", ErrorCode.FIELD_BLANK);
        }
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
