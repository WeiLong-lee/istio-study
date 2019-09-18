package com.example.istio.saas.order.controller;


import com.example.istio.saas.order.entity.Item;
import com.example.istio.saas.order.entity.Order;
import com.example.istio.saas.order.external.ProductServiceProxy;
import com.example.istio.saas.order.repo.OrderRepository;
import com.example.istio.saas.order.service.OrderService;
import com.example.istio.saas.order.type.customer.CustomerOrderRequest;
import com.example.istio.saas.order.type.order.CustomerOrderDetails;
import com.example.istio.saas.order.type.product.ItemDto;
import com.example.istio.saas.order.type.product.ItemRequest;
import com.example.istio.saas.order.type.product.ProductDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/api")
public class OrderController {

	
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductServiceProxy productServiceProxy;

    @Autowired
    private OrderService orderService;


    @PostMapping("/save-order")
    public Order save(@RequestBody CustomerOrderRequest request) {
    	log.debug("Persisting the Order request ["+ request +"]");
        return orderRepository.save(Order
                .builder()
                .customerId(request.getCustomerId())
                .externalReference(request.getExternalReference())
                .items((request.getItems() == null) ? null : toItems(request.getItems())).build());
    }

    @GetMapping("/get-orders-by-customer-id")
    public List<CustomerOrderDetails> getCustomerOrders(@RequestParam String customerId) {
    	log.debug("Getting the customer order with customer id ["+ customerId +"]");
        final List<Order> order = orderRepository.findByCustomerId(customerId);
        return order.stream().map(o -> toCustomerOrderDetails(o)).collect(Collectors.toList());
    }

    @GetMapping("/get-order-by-id/{id}")
    public CustomerOrderDetails getOrders(@PathVariable("id") Long orderId) {
    	log.debug("Getting the customer order with order id ["+ orderId +"]");
        final Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }
        return toCustomerOrderDetails(order);
    }

    @GetMapping("/get-all-order")
    public List<CustomerOrderDetails> getAllOrder() {
        log.debug("Getting all order ");
        final List<Order> orders = orderRepository.findAll();
        if (orders == null || orders.size() ==0) {
            return null;
        }
        return orders.stream().map(o -> toCustomerOrderDetails(o)).collect(Collectors.toList());
    }

    @GetMapping("/get-product-by-order-id/{id}")
    public List<ProductDto> getProductByOrderId(@PathVariable("id") Long orderId){
        log.debug(" getProductByOrderId orderId:{}",orderId);
        return orderService.getProductByOrderId(orderId);
    }

    private CustomerOrderDetails toCustomerOrderDetails(Order order) {
        return CustomerOrderDetails.builder()
                .orderId(order.getId())
                .createdDate(order.getCreatedDate())
                .externalReference(order.getExternalReference())
                .items(toItemList(order.getItems()))
                .version("v2")
                .build();
    }

    private List<ItemDto> toItemList(List<Item> items) {
        return items.stream().map(item -> toItemDto(item)).collect(Collectors.toList());
    }

    private ItemDto toItemDto(Item item) {
        return ItemDto
                .builder()
                .quantity(item.getQuantity())
                .product(productServiceProxy.getProduct(item.getProductId())).build();
    }

    private List<Item> toItems(List<ItemRequest> items) {
        return items.stream().map(itemReq -> Item.builder().productId(itemReq.getProductId())
                .quantity(itemReq.getQuantity()).build())
                .collect(Collectors.toList());
    }

}

