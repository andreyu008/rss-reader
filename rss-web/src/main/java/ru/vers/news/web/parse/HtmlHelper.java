package ru.vers.news.web.parse;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class HtmlHelper {

  public String getElementshtml(String link, Boolean flagVisible) {
    String text = null;
    String img = null;
    String result = null;
    try {
      Document document = Jsoup.connect(link)
          .get();
        Elements pElements = document.select("p");
        text = pElements.html();
        if (flagVisible) {
          Element iElements = document.select("img").first();
          img = iElements.toString();
          return result = img  + " <br> "+ text;

        }
      } catch (IOException e) {
        e.printStackTrace();
      }
     result = text;
    return result;
  }

}
