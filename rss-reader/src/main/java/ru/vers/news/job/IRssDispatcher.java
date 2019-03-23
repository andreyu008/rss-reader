package ru.vers.news.job;

import org.springframework.batch.core.Job;
import ru.vers.news.domain.enums.Imports;

public interface IRssDispatcher {

  //void load(Imports imports, Flow flow);

  Job getJob(Imports imports);

}
