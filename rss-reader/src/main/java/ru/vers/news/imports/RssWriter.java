package ru.vers.news.imports;

import java.util.List;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vers.news.domain.Rss;
import ru.vers.news.domain.RssItems;
import ru.vers.news.repository.RefRssDetailsRepository;
import ru.vers.news.repository.RssItemsRepository;

@Component
@StepScope
public class RssWriter implements ItemWriter<Rss> {

  @Autowired
  private RefRssDetailsRepository refRssDetailsRepository;
  @Autowired
  private RssItemsRepository rssItemsRepository;

  @Override
  public void write(List<? extends Rss> items){
    for (final Rss item : items){
      refRssDetailsRepository.save(item.getRefRssDetails());

      if (item.getRefRssDetails() != null){
        for (RssItems rssItems : item.getRssItems()) {
          rssItems.setIdRssDetail(item.getRefRssDetails().getIdRssDetail());
        }
      }
      rssItemsRepository.saveAll(item.getRssItems());
    }

  }
}
