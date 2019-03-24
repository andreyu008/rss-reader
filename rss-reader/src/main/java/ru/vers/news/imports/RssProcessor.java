package ru.vers.news.imports;

import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vers.news.domain.RefRssDetails;
import ru.vers.news.domain.Rss;
import ru.vers.news.domain.RssItems;
import ru.vers.news.domain.dto.RefRssDetailsDto;
import ru.vers.news.domain.dto.RssItemsDto;
import ru.vers.news.repository.RefRssDetailsRepository;

@Component
@StepScope
public class RssProcessor implements ItemProcessor<RefRssDetailsDto, Rss> {

  @Getter
  @Setter
  private Rss rss;
  @Autowired
  private RefRssDetailsRepository refRssDetailsRepository;

  @Override
  public Rss process(RefRssDetailsDto item) throws Exception {

    final RefRssDetails tempRssDetail = this.refRssDetailsRepository.findByDescription(item.getChannelDto().getDescription())
        .orElse(new RefRssDetails());
    RssItems tempRssItems;
    this.rss = new Rss();
    tempRssDetail.setTitle(item.getChannelDto().getTitle());
    tempRssDetail.setDescription(item.getChannelDto().getDescription());
    tempRssDetail.setLanguage(item.getChannelDto().getLanguage());
    tempRssDetail.setLink(item.getChannelDto().getLink());
    tempRssDetail.setImageUrl(item.getChannelDto().getImageDto().getImageUrl());
    tempRssDetail.setImageTitle(item.getChannelDto().getImageDto().getImageTitle());
    tempRssDetail.setImageLink(item.getChannelDto().getImageDto().getImageLink());
    tempRssDetail.setImageWidth(item.getChannelDto().getImageDto().getImageWidth());
    tempRssDetail.setImageHeight(item.getChannelDto().getImageDto().getImageHeight());

    for (RssItemsDto rssItems : item.getChannelDto().getRssItemsDto()) {
      tempRssItems = new RssItems();
      tempRssItems.setTitle(rssItems.getTitle());
      tempRssItems.setDescription(rssItems.getDescription());
      tempRssItems.setLink(rssItems.getLink());
      this.rss.getRssItems().add(tempRssItems);
    }

    this.rss.setRefRssDetails(tempRssDetail);

    return this.rss;
  }
}
