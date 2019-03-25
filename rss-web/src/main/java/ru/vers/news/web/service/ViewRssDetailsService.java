package ru.vers.news.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vers.news.web.domain.RefRssDetails;
import ru.vers.news.web.repository.RefRssDetailsRepository;

@Service
public class ViewRssDetailsService {

  @Autowired
  private RefRssDetailsRepository refRssDetailsRepository;

  /**
   * Весь список подписок.
   * @return список подписок.
   */
  public List<RefRssDetails> retrieveData(){
    return this.refRssDetailsRepository.findAll();
  }

}
