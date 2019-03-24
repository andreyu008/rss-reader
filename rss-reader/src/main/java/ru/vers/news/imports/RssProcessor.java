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
import ru.vers.news.repository.RssItemsRepository;

@Component
@StepScope
public class RssProcessor implements ItemProcessor<RefRssDetailsDto, Rss> {

  @Getter
  @Setter
  private Rss rss;
  @Autowired
  private RefRssDetailsRepository refRssDetailsRepository;
  @Autowired
  private RssItemsRepository rssItemsRepository;

  @Override
  public Rss process(RefRssDetailsDto item) {
    final RefRssDetails tempRssDetail = this.refRssDetailsRepository
        .findByDescription(item.getChannelDto().getDescription())
        .orElse(new RefRssDetails());
    getRssItem(item, tempRssDetail);
    this.rss = new Rss();
    this.rss.setRefRssDetails(tempRssDetail);
    return this.rss;
  }

  private void getRssItem(RefRssDetailsDto item, RefRssDetails tempRssDetail) {
    RssItems tempRssItems;
    tempRssDetail.setTitle(item.getChannelDto().getTitle());
    tempRssDetail.setDescription(item.getChannelDto().getDescription());
    tempRssDetail.setLanguage(item.getChannelDto().getLanguage());
    tempRssDetail.setLink(item.getChannelDto().getLink());
    tempRssDetail.setImageUrl(item.getChannelDto().getImageDto() != null
        ? item.getChannelDto().getImageDto().getImageUrl() : null);
    tempRssDetail.setImageTitle(item.getChannelDto().getImageDto() != null ?
        item.getChannelDto().getImageDto().getImageTitle() : null);
    tempRssDetail.setImageLink(item.getChannelDto().getImageDto() != null ?
        item.getChannelDto().getImageDto().getImageLink() : null);
    tempRssDetail.setImageWidth(item.getChannelDto().getImageDto() != null ?
        item.getChannelDto().getImageDto().getImageWidth() : null);
    tempRssDetail.setImageHeight(item.getChannelDto().getImageDto() != null ?
        item.getChannelDto().getImageDto().getImageHeight() : null);

    for (RssItemsDto rssItems : item.getChannelDto().getRssItemsDto()) {
      tempRssItems = this.rssItemsRepository.findByTitle(rssItems.getTitle())
          .orElse(new RssItems());
      tempRssItems.setTitle(rssItems.getTitle());
      tempRssItems.setDescription(rssItems.getDescription());
      tempRssItems.setLink(rssItems.getLink());
      this.rss.getRssItems().add(tempRssItems);
    }
  }
}
