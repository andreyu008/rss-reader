package ru.vers.news.job;

import ru.vers.news.domain.enums.Imports;

public interface IRssLoader {

  void load(Imports imports);

}
