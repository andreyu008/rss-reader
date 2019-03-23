package ru.vers.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.vers.news.domain.enums.Imports;
import ru.vers.news.job.IRssLoader;

@RestController
@RequestMapping("/imports")
public class ImportsController {

  @Autowired
  private IRssLoader iRssLoader;

  @RequestMapping(value = "/{imports}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.OK)
  public void loadNsi(@PathVariable("imports") final String imports) {
    this.iRssLoader.load(Imports.from(imports));
  }

}
