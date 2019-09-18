package com.example.istio.saas.order.type.customer;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Address address;
}
