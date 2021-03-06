package ru.vers.news.domain.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement(name = RefRssDetailsDto.ROOT_ELEMENT)
@XmlAccessorType(XmlAccessType.FIELD)
public class RefRssDetailsDto {
  public static final String ROOT_ELEMENT = "rss";

  @XmlElement(name = "channel")
  private ChannelDto channelDto;


}
