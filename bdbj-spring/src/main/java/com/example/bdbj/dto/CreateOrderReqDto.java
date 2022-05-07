package com.example.bdbj.dto;

import com.example.bdbj.domain.OrderItem;
import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.FieldBlankException;

import java.util.List;

public class CreateOrderReqDto {
    private final String phoneNumber;
    private final String address;
    private final String detailedAddress;
    private final String postcode;
    private final List<OrderItem> orderItems;

    public CreateOrderReqDto(String phoneNumber, String address, String detailedAddress, String postcode, List<OrderItem> orderItems) {
        if (phoneNumber == null || address == null || detailedAddress == null || postcode == null || orderItems == null) {
            throw new FieldBlankException("입력값이 부족합니다.", ErrorCode.FIELD_BLANK);
        }
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.postcode = postcode;
        this.orderItems = orderItems;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
