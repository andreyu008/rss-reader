package ru.vers.news.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.vers.news.domain.RefImportRss;
import ru.vers.news.domain.enums.Imports;
import ru.vers.news.exception.RunJobException;
import ru.vers.news.exception.UncheckedJobExecutionAlreadyRunningException;
import ru.vers.news.exception.UncheckedJobRestartException;
import ru.vers.news.service.RefRssService;

@Service
@Slf4j
public class RssLoader implements IRssLoader {

  @Autowired
  private RefRssService refRssService;
  @Autowired
  private JobLauncher jobLauncher;
  @Autowired
  private IRssDispatcher nsiDispatcher;
  @Value("${imports.scheduled.enabled:false}")
  boolean enabled;

  @Scheduled(cron = "${imports.scheduled.cron: * * 23 * * *}")
  public void autoLoadNsi() throws InterruptedException {
    if (this.enabled) {
      log.info("START AUTOLOAD");
      for (RefImportRss rss : this.refRssService.orderNsi()) {
        load(Imports.from(rss.getShortName()));
        Thread.sleep(50L);
      }
      log.info("FINISH AUTOLOAD");
    }
  }


  @Override
  public void load(Imports imports) {

    try {
      final Job job = nsiDispatcher.getJob(imports);
      final JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
      jobParametersBuilder.addString(Constants.RSS, imports.name());
      jobParametersBuilder.addLong(Constants.TIME, System.currentTimeMillis());
      this.jobLauncher.run(job, jobParametersBuilder.toJobParameters());
      refRssService.saveSuccess(imports);
    } catch (JobExecutionAlreadyRunningException e) {
      throw new UncheckedJobExecutionAlreadyRunningException("Загрузка уже запущена", e);
    } catch (JobRestartException e) {
      refRssService.saveError(imports);
      throw new UncheckedJobRestartException("Ошибка при рестарте загрузки", e);
    } catch (Exception e) {
      refRssService.saveError(imports);
      throw new RunJobException("Ошибка при запуске загрузки", e);
    }
  }
}
