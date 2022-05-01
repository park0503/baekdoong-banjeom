package com.example.bdbj.domain;

import lombok.Builder;
import lombok.NonNull;

public class Address {
    private final String address;
    private final String detailedAddress;
    private final String postcode;

    @Builder
    public Address(@NonNull String address, @NonNull String detailedAddress, @NonNull String postcode) {
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.postcode = postcode;
    }
}
