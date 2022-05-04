package com.example.bdbj.dto;

import com.example.bdbj.domain.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateOrderReqDto {
    private final String phoneNumber;
    private final String address;
    private final String detailedAddress;
    private final String postcode;
    private final List<OrderItem> orderItems;

    public CreateOrderReqDto(String phoneNumber, String address, String detailedAddress, String postcode, List<OrderItem> orderItems) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.postcode = postcode;
        this.orderItems = orderItems;
    }
}
