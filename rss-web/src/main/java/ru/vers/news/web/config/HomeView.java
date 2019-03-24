package ru.vers.news.web.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeView {


  @GetMapping("/**")
  public String getIndexPage() {
    return "index";
  }
}


