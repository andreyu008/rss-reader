package ru.vers.news.job.step;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import ru.vers.news.domain.RefImportRss;
import ru.vers.news.domain.enums.Imports;
import ru.vers.news.service.RefRssService;

@Service
public class RssResourceFinder implements IRssResourceFinder {

  @Autowired
  private RefRssService refRssService;

  @Override
  public Resource findResource(Imports imports) {
    try {
      RefImportRss refImportRss = refRssService.findById(imports);
      String path = refImportRss.getPage();
      UrlResource urlResource = new UrlResource(new URL(path));
      return urlResource;
    }catch (IOException e){
      throw new UncheckedIOException(e);
    }

  }
}
