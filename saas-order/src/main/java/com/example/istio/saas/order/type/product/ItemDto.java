package com.example.istio.saas.order.type.product;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemDto {
    private ProductDto product;
    private BigDecimal quantity;

}
