package ru.vers.news.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vers.news.domain.RefImportRss;
import ru.vers.news.domain.enums.Imports;
import ru.vers.news.domain.enums.State;
import ru.vers.news.exception.ResourceNotFoundException;
import ru.vers.news.repository.RefRssRepository;

@Service
public class RefRssService {

  @Autowired
  private RefRssRepository refRssRepository;

  public void save(RefImportRss refImportRss) {
    RefImportRss refImportNsiForSave = findById(Imports.from(refImportRss.getShortName()));
    refRssRepository.save(refImportNsiForSave);
  }

  public void saveSuccess(Imports imports) {
    RefImportRss refImportRss = this.findById(imports);
    refImportRss.setState(State.SUCCESS);
    refRssRepository.save(refImportRss);
  }

  public void saveError(Imports imports) {
    RefImportRss refImportRss = this.findById(imports);
    refImportRss.setState(State.ERROR);
    refRssRepository.save(refImportRss);
  }

  public RefImportRss findById(Imports imports) {
    return this.refRssRepository.findById(imports.name())
        .orElseThrow(ResourceNotFoundException::new);
  }

  public List<RefImportRss> orderNsi() {
    return this.refRssRepository.findAllByOrderByNumAsc();
  }

}
