package com.example.bdbj.domain;

import com.example.bdbj.domain.error.ErrorCode;
import com.example.bdbj.exception.FieldBlankException;

import java.util.Objects;

public class Address {
    private final String address;
    private final String detailedAddress;
    private final String postcode;

    public Address(String address, String detailedAddress, String postcode) {
        checkInput(address, detailedAddress, postcode);
        this.address = address;
        this.detailedAddress = detailedAddress;
        this.postcode = postcode;
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

    private void checkInput(String address, String detailedAddress, String postcode) {
        if (address == null || detailedAddress == null || postcode == null) {
            throw new FieldBlankException("주소값을 확인해주세요.", ErrorCode.FIELD_BLANK);
        }
    }
}
