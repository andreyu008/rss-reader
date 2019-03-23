package ru.vers.news.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vers.news.domain.RefImportRss;

public interface RefRssRepository extends JpaRepository<RefImportRss, String> {

  @Transactional(readOnly = true)
  List<RefImportRss> findAllByOrderBySourceTypeAscFullNameAsc();

  @Transactional(readOnly = true)
  List<RefImportRss> findAllByOrderByNumAsc();

}
