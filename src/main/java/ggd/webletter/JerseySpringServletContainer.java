package ggd.webletter;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.ListableBeanFactory;

import javax.ws.rs.Path;
import java.util.Map;

public class JerseySpringServletContainer extends ServletContainer {

    public JerseySpringServletContainer(ListableBeanFactory listableBeanFactory) {
        super(new JerseyApplication(listableBeanFactory));
    }

    public static class JerseyApplication extends ResourceConfig {

        public JerseyApplication(ListableBeanFactory listableBeanFactory) {
            Map<String, Object> resources = listableBeanFactory.getBeansWithAnnotation(Path.class);
            for (Map.Entry<String, Object> resource : resources.entrySet()) {
                System.out.println("Adding resource " + resource.getKey());
                register(resource.getValue());
            }
        }

    }

}
