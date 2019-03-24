package ru.vers.news.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vers.news.web.domain.RssItems;
import ru.vers.news.web.repository.RssItemsRepository;

@Service
public class ViewRssItemsService {

  @Autowired
  private RssItemsRepository rssItemsRepository;

  public List<RssItems> retrieveRssItems(Long idRssDetails) {
    return this.rssItemsRepository.findByIdRssDetailOrderByIdRssItemsAsc(idRssDetails);

  }

  public RssItems getItem(Long idRssItems) {
    return this.rssItemsRepository.findById(idRssItems).get();
  }

}
