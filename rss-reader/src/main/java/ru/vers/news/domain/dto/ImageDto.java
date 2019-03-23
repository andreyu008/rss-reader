package ru.vers.news.domain.dto;

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
public class ImageDto {

  @XmlElement(name = "title")
  private String imageTitle;
  @XmlElement(name = "url")
  private String imageUrl;
  @XmlElement(name = "link")
  private String imageLink;
  @XmlElement(name = "width")
  private String imageWidth;
  @XmlElement(name = "height")
  private String imageHeight;

}
