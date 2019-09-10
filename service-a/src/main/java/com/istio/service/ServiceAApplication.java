package com.istio.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ServiceAApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceAApplication.class, args);
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

  @Value("${app.version:1.0}")
  private String version;
  @Value("${service-b.url}")
  private String urlB;
  @Value("${service-c.url}")
  private String urlC;
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/info")
  public String info() {
    String rsp = "A Service version = " + this.version +
              "  ===> " + notify(urlB + "/info") +
              "  ===> " + notify(urlC +"/info");
    log.info(rsp);
    return rsp;
  }

  public String notify(String url) {
    try {
      return restTemplate.getForObject(url, String.class);
    } catch (RestClientException e) {
      return e.getMessage();
    }
  }
}
