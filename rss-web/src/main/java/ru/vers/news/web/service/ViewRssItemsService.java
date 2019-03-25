package ru.vers.news.web.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vers.news.web.domain.RssItems;
import ru.vers.news.web.repository.RssItemsRepository;

@Service
public class ViewRssItemsService {

  @Autowired
  private RssItemsRepository rssItemsRepository;

  /**
   * Получение списка тайтлов.
   * @param idRssDetails id записи подписки.
   * @return список тайтлов.
   */
  public List<RssItems> retrieveRssItems(Long idRssDetails) {
    return this.rssItemsRepository.findByIdRssDetailOrderByIdRssItemsAsc(idRssDetails);

  }

  /**
   * Получение тайтла.
   * @param idRssItems идендификатор тайтла.
   * @return тайтл.
   */
  public RssItems getItem(Long idRssItems) {
    return this.rssItemsRepository.findById(idRssItems).get();
  }

  /**
   * Поиск тайтла из всего списка.
   * @param keyword значание введенное в строку поиска
   * @return результат поиска.
   */
  public List<RssItems> search(String keyword){
    List<RssItems> itemsList;
    itemsList = this.rssItemsRepository.findAll();
    List<RssItems> result = new LinkedList<>();
    if (keyword==null || "".equals(keyword)){
      result = itemsList;
    }else{
      for (RssItems c: itemsList){
        if (c.getTitle().toLowerCase().contains(keyword.toLowerCase())){
          result.add(c);
        }
      }
    }
    return result;
  }

}
