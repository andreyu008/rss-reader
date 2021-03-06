package ru.vers.news.domain.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class ChannelDto {

  @XmlElement(name = "title")
  private String title;
  @XmlElement(name = "description")
  private String description;
  @XmlElement(name = "link")
  private String link;
  @XmlElement(name = "language")
  private String language;
  @XmlElement(name = "image")
  private ImageDto imageDto;
  @XmlElement(name = "item")
  private List<RssItemsDto> rssItemsDto;

}
