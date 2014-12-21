package ggd.webletter;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebServer {

    private final Server server;

    private WebServer(Server server) {
        this.server = server;
    }

    public static WebServer create(Integer port) {
        Server server = new Server(port);
        server.setHandler(servletContextHandler(getContext()));
        return new WebServer(server);
    }

    private static ServletContextHandler servletContextHandler(WebApplicationContext context) {
      ServletContextHandler contextHandler = new ServletContextHandler();
      contextHandler.addServlet(new ServletHolder(new SpringServlet()), "/*");
      contextHandler.addEventListener(new ContextLoaderListener(context));
  //    contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
      return contextHandler;
    }

    private static WebApplicationContext getContext() {
      AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
      context.scan(Main.class.getPackage().getName());
      return context;
    }

    public void start() {
        start(false);
    }

    public void start(boolean background) {
        try {
            server.start();
            if(! background){
                server.join();
            }
        } catch (Exception e) {
            throw new RuntimeException("Coulld not start server", e);
        }
    }
}
