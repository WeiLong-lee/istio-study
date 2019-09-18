package com.example.istio.saas.order.type.order;


import com.example.istio.saas.order.type.customer.Customer;
import com.example.istio.saas.order.type.product.ItemDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerOrderDetails {

    private Long orderId;
    private String externalReference;
    private Customer customer;
    private LocalDateTime createdDate;
    private List<ItemDto> items;
    private BigDecimal totalOrderCost;
    private BigDecimal totalOrderTax;
    private String version;
    
}
