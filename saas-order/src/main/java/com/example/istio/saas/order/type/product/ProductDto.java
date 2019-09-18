package com.example.istio.saas.order.type.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private String price;
    private String sku;
}
