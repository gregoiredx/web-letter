package ggd.webletter;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.glassfish.jersey.servlet.ServletContainer;
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
            register(FreemarkerMvcFeature.class);
        }

        private void registerBeansWithAnnotation(ListableBeanFactory listableBeanFactory, Class<? extends Annotation> anAnnotation) {
            Map<String, Object> resources = listableBeanFactory.getBeansWithAnnotation(anAnnotation);
            for (Map.Entry<String, Object> resource : resources.entrySet()) {
                System.out.println("Adding resource " + resource.getKey());
                register(resource.getValue());
            }
        }

    }

}
