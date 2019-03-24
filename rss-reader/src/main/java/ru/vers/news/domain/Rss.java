package ru.vers.news.domain;


import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class Rss {

  private RefRssDetails refRssDetails;
  //private RssItems rssItems;
  private List<RssItems> rssItems = new ArrayList<>();

}
