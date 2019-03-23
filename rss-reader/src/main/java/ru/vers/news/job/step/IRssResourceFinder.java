package ru.vers.news.job.step;

import org.springframework.core.io.Resource;
import ru.vers.news.domain.enums.Imports;

public interface IRssResourceFinder {

  Resource findResource(Imports imports);

}
