package com.example.istio.saas.order.external;

import com.example.istio.saas.order.type.product.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product", url = "http://${product-service.service.host}:${product-service.service.port}")
@Service
public interface ProductServiceProxy {

    @GetMapping("/product/api/get-product-by-id/{id}")
    ProductDto getProduct(@PathVariable("id") Long id);
}
