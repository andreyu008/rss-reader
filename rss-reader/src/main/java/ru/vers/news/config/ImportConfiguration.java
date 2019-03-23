package ru.vers.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ImportConfiguration {

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

}
