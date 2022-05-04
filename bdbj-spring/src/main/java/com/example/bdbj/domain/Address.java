package com.example.bdbj.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) && Objects.equals(detailedAddress, address1.detailedAddress) && Objects.equals(postcode, address1.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, detailedAddress, postcode);
    }
}
