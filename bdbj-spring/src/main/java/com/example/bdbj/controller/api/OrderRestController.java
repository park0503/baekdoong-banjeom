package com.example.bdbj.controller.api;

import com.example.bdbj.domain.Address;
import com.example.bdbj.dto.CreateOrderReqDto;
import com.example.bdbj.dto.OrderResDto;
import com.example.bdbj.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new")
    public ResponseEntity<OrderResDto> createOrder(@RequestBody CreateOrderReqDto createOrderReqDto) {
        return new ResponseEntity<>(
                new OrderResDto(
                        orderService.createOrder(
                                createOrderReqDto.getPhoneNumber(),
                                new Address(createOrderReqDto.getAddress(),
                                        createOrderReqDto.getDetailedAddress(),
                                        createOrderReqDto.getPostcode()),
                                createOrderReqDto.getOrderItems()
                        )
                ),
                HttpStatus.OK
        );
    }
}
