package ru.vers.news.job.step;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class RssXmlReader<T> {

  /**
   * Получение Reader.
   *
   * @param resource бин, для загрузки xml
   * @param type класс на который будет маппироваться xml
   * @return Reader
   */

  public StaxEventItemReader<T> getReader(Resource resource, Class<T> type, String rootElement) {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setClassesToBeBound(type);
    StaxEventItemReader<T> xmlStaxEventItemReader = new StaxEventItemReader<>();
    xmlStaxEventItemReader.setUnmarshaller(marshaller);
    xmlStaxEventItemReader.setResource(resource);
    xmlStaxEventItemReader.setFragmentRootElementName(rootElement);
    xmlStaxEventItemReader.open(new ExecutionContext());
    return xmlStaxEventItemReader;
  }

}
