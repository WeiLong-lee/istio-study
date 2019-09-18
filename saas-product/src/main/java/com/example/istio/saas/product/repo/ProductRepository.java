package com.example.istio.saas.product.repo;


import com.example.istio.saas.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
