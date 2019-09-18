package com.example.istio.saas.order.type.customer;


import com.example.istio.saas.order.type.product.ItemRequest;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;


@Data
public class CustomerOrderRequest {

    private String customerId;
    private String externalReference;
    private LocalDateTime createdDate;
    private List<ItemRequest> items;
}
