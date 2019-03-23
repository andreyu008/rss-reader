package ru.vers.news.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.vers.news.domain.RefImportRss;
import ru.vers.news.domain.enums.Imports;
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

  }
}
