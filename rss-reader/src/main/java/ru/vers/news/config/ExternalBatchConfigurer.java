package ru.vers.news.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class ExternalBatchConfigurer extends DefaultBatchConfigurer {

  private final LocalContainerEntityManagerFactoryBean entityManagerFactory;

  @Autowired
  public ExternalBatchConfigurer(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @Bean
  @Qualifier("jpaTransactionManager")
  public PlatformTransactionManager transactionManager() {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(this.entityManagerFactory.getObject());
    return transactionManager;
  }
}
