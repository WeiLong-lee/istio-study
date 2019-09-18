package com.example.istio.saas.product.controller;

import com.example.istio.saas.product.entity.Product;
import com.example.istio.saas.product.mapper.ProductMapper;
import com.example.istio.saas.product.repo.ProductRepository;

import java.util.List;
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
public class ProductController {

	
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/get-products")
    public List<Product> getProducts() {
    	log.debug("Getting all the products..");
        return productRepository.findAll();
    }

    @GetMapping("/get-product-by-id/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
    	log.debug("Getting the products with id ["+ id +"]");
        Product product = productRepository.findById(id).orElse(new Product(-1L, "Not Available", "Product Not avilable", "-1", null));
        return product;
    }

    @PostMapping("/save-product")
    public Product saveProduct(@RequestBody Product input) {
    	log.debug("Persisting the products details ["+ input +"]");
    	Product product =  productRepository.save(input);
        return product;
    }
    @GetMapping("/get-product-by-ids")
    public List<Product> getProductsById(@RequestParam("ids") List<Long> ids){
      return productMapper.selectBatchIds(ids);
    }
}

