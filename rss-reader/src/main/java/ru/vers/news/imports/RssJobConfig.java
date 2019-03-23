package ru.vers.news.imports;

import javax.persistence.EntityManagerFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RssJobConfig {

  private static final String READER = "reader";
  private static final String WRITER = "WRITER";
  private static final String READ_WRITE_STEP = "read_write";
  public static final String DOCUMENTS_JOB = "DOCUMENTS_JOB";

  @Autowired
  private EntityManagerFactory entityManagerFactory;
  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  @Qualifier("jpaTransactionManager")
  private PlatformTransactionManager jpaTransactionManager;


/*  @Bean(READER)
  @StepScope
  public StaxEventItemReader<RefTfomsDto> reader(INsiResourceFinder nsiResourceFinder,
      @Value("#{jobParameters['NSI']}") final String nsi) {
    final NsiXmlReader<RefRssDto> reader = new NsiXmlReader<>();
    return reader.getReader(nsiResourceFinder.findResource(Nsi.valueOf(nsi)),
        RefTfomsDto.class, "rss");
  }

  @Bean(WRITER)
  public JpaItemWriter<RefReestrMo> writer() {
    final JpaItemWriter<RefReestrMo> jpaItemWriter = new JpaItemWriter<>();
    jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
    return jpaItemWriter;
  }*/

}
