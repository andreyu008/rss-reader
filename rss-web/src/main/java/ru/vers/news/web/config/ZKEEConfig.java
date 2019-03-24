package ru.vers.news.web.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.zkoss.zkmax.ui.comet.CometAsyncServlet;

@Import(ZKCEConfig.class)
public class ZKEEConfig {

  @Bean
  public ServletContextInitializer manualServletConfigInit() {
    return servletContext -> {
      // required to avoid duplicate installing of the CometAsyncServlet
      // startup sequence in spring boot is different to a normal servlet webapp
      servletContext.setAttribute("org.zkoss.zkmax.ui.comet.async.installed", true);
      servletContext.setAttribute("org.zkoss.zkmax.au.websocket.filter.installed", true);
    };
  }

  @Bean
  public ServletRegistrationBean cometAsyncServlet() {
    ServletRegistrationBean reg =
        new ServletRegistrationBean(new CometAsyncServlet(), "/zkcomet/*");
    reg.setAsyncSupported(true);
    return reg;
  }

}
