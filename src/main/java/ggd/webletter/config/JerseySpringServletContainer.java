package ggd.webletter.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.ListableBeanFactory;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.util.Map;

public class JerseySpringServletContainer extends ServletContainer {

    public JerseySpringServletContainer(ListableBeanFactory listableBeanFactory) {
        super(new JerseyApplication(listableBeanFactory));
    }

    public static class JerseyApplication extends ResourceConfig {

        public JerseyApplication(ListableBeanFactory listableBeanFactory) {
            registerBeansWithAnnotation(listableBeanFactory, Path.class);
            registerBeansWithAnnotation(listableBeanFactory, Provider.class);
            property(FreemarkerMvcFeature.TEMPLATES_BASE_PATH, "templates");
            property(ServletProperties.FILTER_STATIC_CONTENT_REGEX, "/static");
            register(FreemarkerMvcFeature.class);
        }

        private void registerBeansWithAnnotation(ListableBeanFactory listableBeanFactory, Class<? extends Annotation> anAnnotation) {
            Map<String, Object> resources = listableBeanFactory.getBeansWithAnnotation(anAnnotation);
            for (Map.Entry<String, Object> resource : resources.entrySet()) {
                register(resource.getValue());
            }
        }

    }

}
