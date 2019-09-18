package com.example.istio.saas.order.type.customer;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String postcode;
}
