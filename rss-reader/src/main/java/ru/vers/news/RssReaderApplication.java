package ru.vers.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class RssReaderApplication {

  public static void main(final String[] args) {
    SpringApplication.run(RssReaderApplication.class);
  }

}
