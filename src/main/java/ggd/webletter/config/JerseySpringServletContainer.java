package ggd.webletter.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.ListableBeanFactory;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import static org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature.TEMPLATES_BASE_PATH;
import static org.glassfish.jersey.servlet.ServletProperties.FILTER_STATIC_CONTENT_REGEX;

public class JerseySpringServletContainer extends ServletContainer {

    public JerseySpringServletContainer(ListableBeanFactory listableBeanFactory) {
        super(new JerseyApplication(listableBeanFactory));
    }

    public static class JerseyApplication extends ResourceConfig {

        public JerseyApplication(ListableBeanFactory beanFactory) {
            beanFactory.getBeansWithAnnotation(Path.class).values().forEach(this::register);
            beanFactory.getBeansWithAnnotation(Provider.class).values().forEach(this::register);
            register(FreemarkerMvcFeature.class).property(TEMPLATES_BASE_PATH, "templates");
            property(FILTER_STATIC_CONTENT_REGEX, "/static");
        }

    }

}
