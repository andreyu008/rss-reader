package ru.vers.news.imports;

import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import ru.vers.news.support.FileUtils;

@Getter
@ConfigurationProperties("rss")
@Validated
public class RssProperties {

  /**
   * Директория загруженных xml.
   */
  @NotEmpty
  @Setter
  private String upload;

  private Path uploadPath;

  @PostConstruct
  public void init() {
    this.uploadPath = FileUtils.createDirectory(Paths.get(this.getUpload()));
  }

}
