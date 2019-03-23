package ru.vers.news.job;

import lombok.Getter;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.vers.news.domain.enums.Imports;
import ru.vers.news.imports.RssJobConfig;

@Component
@Getter
public class RssDispatcher implements IRssDispatcher {


  @Autowired
  @Qualifier(RssJobConfig.RSS_JOB)
  private Job rssJobConfig;

  @Override
  public Job getJob(Imports imports) {
    return this.rssJobConfig;
  }
}
