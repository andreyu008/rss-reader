package ru.vers.news.imports;

import javax.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import ru.vers.news.domain.Rss;
import ru.vers.news.domain.dto.RefRssDetailsDto;
import ru.vers.news.domain.enums.Imports;
import ru.vers.news.job.step.DownloadFileTasklet;
import ru.vers.news.job.step.IRssResourceFinder;
import ru.vers.news.job.step.RssXmlReader;

@Configuration
public class RssJobConfig {

  private static final String READER = "reader";
  private static final String WRITER = "WRITER";
  private static final String READ_WRITE_STEP = "read_write";
  private static final String DOWNLOAD_FILE_STEP = "DOWNLOAD_FILE_STEP";
  public static final String RSS_JOB = "RSS_JOB";

  @Autowired
  private EntityManagerFactory entityManagerFactory;
  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  @Qualifier("jpaTransactionManager")
  private PlatformTransactionManager jpaTransactionManager;
  @Autowired
  private DownloadFileTasklet downloadFileTasklet;


  @Bean(READER)
  @StepScope
  public StaxEventItemReader<RefRssDetailsDto> reader(IRssResourceFinder rssResourceFinder,
      @Value("#{jobParameters['RSS']}") final String rss) {
    final RssXmlReader<RefRssDetailsDto> reader = new RssXmlReader<>();
    return reader.getReader(rssResourceFinder.findResource(Imports.valueOf(rss)),
        RefRssDetailsDto.class, "rss");
  }

  /*@Bean(READER)
  @StepScope
  public ItemReader<RefRssDetailsDto> reader(
      @Value("#{jobExecutionContext['FILE_NAME']}") final String fileName) throws Exception {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setClassesToBeBound(RefRssDetailsDto.class);
    StaxEventItemReader<RefRssDetailsDto> reader = new StaxEventItemReaderBuilder<RefRssDetailsDto>()
        .name("news")
        .resource(new FileSystemResource(fileName))
        .addFragmentRootElements("rss")
        .unmarshaller(marshaller)
        .build();
    reader.afterPropertiesSet();
    ExecutionContext executionContext = new ExecutionContext();
    reader.open(executionContext);
    return reader;
  }*/
 /* @Bean(WRITER)
  public JpaItemWriter<Rss> writer() {
    final JpaItemWriter<Rss> jpaItemWriter = new JpaItemWriter<>();
    jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
    return jpaItemWriter;
  }*/

  /**
   * Шаг для парсинга xml.
   *
   * @param reader ридер
   * @param processor обработчик
   * @param writer пишет в базу
   * @param stepBuilderFactory фабрика
   * @return шаг
   */
  @Bean(READ_WRITE_STEP)
  public Step parseStep(final ItemReader<RefRssDetailsDto> reader,
      final ItemProcessor<RefRssDetailsDto, Rss> processor,
      final ItemWriter<Rss> writer,
      final StepBuilderFactory stepBuilderFactory) {
    return stepBuilderFactory.get(READ_WRITE_STEP)
        .transactionManager(this.jpaTransactionManager)
        .<RefRssDetailsDto, Rss>chunk(Integer.MAX_VALUE).reader(reader)
        .processor(processor).writer(writer).build();
  }

  @Bean(DOWNLOAD_FILE_STEP)
  public Step downloadFileStep(final StepBuilderFactory stepBuilderFactory) {
    return stepBuilderFactory.get(DOWNLOAD_FILE_STEP).tasklet(this.downloadFileTasklet).build();

  }

  @Bean(RSS_JOB)
  public Job rssJob(@Qualifier(READ_WRITE_STEP) final Step step/*,
      @Qualifier(DOWNLOAD_FILE_STEP) final Step downLoadStep*/){

    return this.jobBuilderFactory.get(RSS_JOB).incrementer(new RunIdIncrementer())
        .start(step)/*.next(step)*/.build();
  }

}
