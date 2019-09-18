package com.example.istio.saas.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
@MapperScan({"com.example.istio.saas.product.mapper"})
public class SaasProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(SaasProductApplication.class, args);
  }

}
