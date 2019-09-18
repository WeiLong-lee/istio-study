package com.example.istio.saas.order.type.product;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ItemRequest {

    private Long productId;
    private BigDecimal quantity;
}
