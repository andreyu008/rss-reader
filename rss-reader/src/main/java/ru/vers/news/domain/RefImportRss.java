package ru.vers.news.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.vers.news.domain.enums.State;

@Entity
@Table(name = "ref_import_rss", schema = "news")
@Getter
@Setter
@EqualsAndHashCode(of = {"shortName"})
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefImportRss implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * Короткое имя
   */
  @Id
  @Column(name = "short_name")
  private String shortName;

  /**
   * источник.
   */
  @Column(name = "src")
  private String sourceType;

  /**
   * Статус последней загрузки.
   */
  @Column(name = "state")
  @Enumerated(EnumType.STRING)
  private State state = State.CREATED;

  /**
   * Наименование.
   */
  @Column(name = "full_name")
  private String fullName;

  /**
   * Адрес страницы
   */
  @Column(name = "page")
  private String page;

  @Column(name = "num")
  private Integer num;

}
