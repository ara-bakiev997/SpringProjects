package edu.spring.configs;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcAppDispatcherServletInitializer extends
    AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[0];
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{WebLibraryConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    registerCharacterEncodingFilter(servletContext);
    registerHiddenFieldFilter(servletContext);
  }

  private void registerCharacterEncodingFilter(ServletContext aContext) {
    EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST,
        DispatcherType.FORWARD);

    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);

    FilterRegistration.Dynamic characterEncoding = aContext.addFilter("characterEncoding",
        characterEncodingFilter);
    characterEncoding.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
  }

  private void registerHiddenFieldFilter(ServletContext aContext) {
    aContext.addFilter("hiddenHttpMethodFilter",
        new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
  }

}
