package com.example.istio.saas.order.service.impl;

import com.example.istio.saas.order.common.service.impl.BaseServiceImpl;
import com.example.istio.saas.order.entity.Item;
import com.example.istio.saas.order.entity.Order;
import com.example.istio.saas.order.mapper.OrderMapper;
import com.example.istio.saas.order.repo.OrderRepository;
import com.example.istio.saas.order.service.OrderService;
import com.example.istio.saas.order.type.product.ProductDto;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper,Order> implements OrderService {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private OrderMapper orderMapper;
  @Autowired
  private RestTemplate restTemplate;
  @Value("${product-service.service.host}")
  private String PRODUCT_SERVICE_HOST;
  @Value("${product-service.service.port}")
  private String PRODUCT_SERVICE_PORT;
  @Override
  public List<ProductDto> getProductByOrderId(Long orderId) {

    /*Order order = orderRepository.getOne(orderId);
    if(Objects.isNull(order) || Objects.isNull(order.getItems()) || order.getItems().size() == 0){
      return null;
    }
    return order.getItems().stream().map(item -> getProductDtoByItem(item)).collect(Collectors.toList());*/
    List<Item> items = orderMapper.selectItemsByOrderId(orderId);
    if(items ==null || items.size() ==0){
      return null;
    }
    return items.stream().map(item -> getProductDtoByItem(item)).collect(Collectors.toList());
  }

  private ProductDto getProductDtoByItem(Item item){
    ResponseEntity<ProductDto> entity = restTemplate.getForEntity(
        "http://"+PRODUCT_SERVICE_HOST + ":" + PRODUCT_SERVICE_PORT
            + "/product/api/get-product-by-id/{id}",
        ProductDto.class, item.getProductId());
    return entity.getBody();
  }
}
