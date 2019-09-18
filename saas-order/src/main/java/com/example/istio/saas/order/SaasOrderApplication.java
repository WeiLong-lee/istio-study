package com.example.istio.saas.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@CrossOrigin
@MapperScan({"com.example.istio.saas.order.mapper"})
public class SaasOrderApplication {

  public static void main(String[] args) {
    SpringApplication.run(SaasOrderApplication.class, args);
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(){
     return new RestTemplate();
  }

}
