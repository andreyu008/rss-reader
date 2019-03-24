package ru.vers.news.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vers.news.web.config.ZKEEApplication;

@ZKEEApplication
@SpringBootApplication
public class RssWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(RssWebApplication.class, args);
  }

}
