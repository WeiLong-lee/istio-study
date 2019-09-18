package com.example.istio.saas.order.service;

import com.example.istio.saas.order.common.service.BaseService;
import com.example.istio.saas.order.entity.Order;
import com.example.istio.saas.order.type.product.ProductDto;
import java.util.List;

public interface OrderService extends BaseService<Order> {

  List<ProductDto> getProductByOrderId(Long orderId);

}
