package ru.vers.news.web.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;
import org.zkoss.zkmax.ui.comet.CometAsyncServlet;

@Configuration
public class ZkossConfig {

  @Bean
  public ServletRegistrationBean cometAsyncServlet() {
    final CometAsyncServlet cometAsyncServlet = new CometAsyncServlet();
    final ServletRegistrationBean reg =
        new ServletRegistrationBean(cometAsyncServlet, "/zkcomet/*");
    reg.setAsyncSupported(true);
    reg.setLoadOnStartup(1);
    return reg;
  }

  @Bean
  public ServletRegistrationBean dHtmlLayoutServlet() {
    final Map<String, String> params = new HashMap<>();
    params.put("update-uri", "/zkau");
    final Set<String> mappings = new HashSet<>();
    mappings.add("*.zul");
    mappings.add("*.zhtml");
    final ServletRegistrationBean bean = new ServletRegistrationBean(new DHtmlLayoutServlet());
    bean.setLoadOnStartup(2);
    bean.setInitParameters(params);
    bean.setUrlMappings(mappings);
    return bean;
  }

  @Bean
  public ServletRegistrationBean dHtmlUpdateServlet() {
    final ServletRegistrationBean bean =
        new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*");
    bean.setLoadOnStartup(3);
    return bean;
  }

}
