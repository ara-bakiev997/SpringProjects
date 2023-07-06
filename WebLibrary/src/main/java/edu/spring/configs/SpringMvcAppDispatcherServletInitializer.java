package edu.spring.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcAppDispatcherServletInitializer extends
    AbstractAnnotationConfigDispatcherServletInitializer {

  protected Class<?>[] getRootConfigClasses() {
    return new Class[0];
  }

  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{WebLibraryConfig.class};
  }

  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}
