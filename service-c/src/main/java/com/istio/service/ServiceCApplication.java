package com.istio.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@EnableAutoConfiguration
@SpringBootApplication
@Configuration
public class ServiceCApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceCApplication.class, args);
  }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

}


@RestController
@Log4j2
@RequestMapping("/api")
class ServiceTestController{

  @Value("${app.version}")
  private String version;

  private RestTemplate restTemplate;

  @GetMapping("/info")
  public String info() {
    String rsp = "C Service version = " + this.version;
    log.info(rsp);
    return rsp;
  }

}
