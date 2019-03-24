package ru.vers.news.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "rss_items", schema = "news")
@Getter
@Setter
@EqualsAndHashCode(of = {"idRssItems"})
@ToString
public class RssItems implements Serializable {

  private static final long serialVersionUID = 1L;


  @SequenceGenerator(name = "RSS_ITEMS_ID_GENERATOR", schema = "news",
      sequenceName = "rss_items_id_rss_items_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RSS_ITEMS_ID_GENERATOR")
  @Id
  private Long idRssItems;
  /*@ManyToOne()
  @JoinColumn(name = "id_rss_detail")*/
  private Long idRssDetail;
  private String title;
  private String description;
  private String link;

}
