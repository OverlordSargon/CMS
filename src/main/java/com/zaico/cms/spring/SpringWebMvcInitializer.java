package com.zaico.cms.spring;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by nzaitsev on 09.11.2016.
 */
public class SpringWebMvcInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                AppConfig.class
        };
    }

    // Тут добавляем конфигурацию, в которой инициализируем ViewResolver
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    //enable lazy loading of JPA entity graphs in view rendering phase
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
//                new DelegatingFilterProxy("springSecurityFilterChain"),
                new OpenEntityManagerInViewFilter()
        };
    }
}
