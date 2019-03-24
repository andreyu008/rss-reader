package ru.vers.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.vers.news.imports.RssProperties;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableConfigurationProperties(value = {RssProperties.class})
public class RssReaderApplication {

  public static void main(final String[] args) {
    SpringApplication.run(RssReaderApplication.class);
  }

}
