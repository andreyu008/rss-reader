package ru.vers.news.web.domain;

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
@Table(name = "ref_rss_details", schema = "news")
@Getter
@Setter
@EqualsAndHashCode(of = {"idRssDetail"})
@ToString
public class RefRssDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  @SequenceGenerator(name = "REF_RSS_DETAILS_ID_GENERATOR", schema = "news",
      sequenceName = "ref_rss_details_id_rss_detail_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REF_RSS_DETAILS_ID_GENERATOR")
  @Id
  private Long idRssDetail;
  private String title;
  private String description;
  private String link;
  private String language;
  private String imageTitle;
  private String imageUrl;
  private String imageLink;
  private String imageWidth;
  private String imageHeight;

}
