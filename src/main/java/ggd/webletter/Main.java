package ggd.webletter;

import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws Exception{
    Server server = new Server(Integer.valueOf(System.getenv("PORT")));
    server.setHandler(getServletContextHandler(getContext()));
    server.start();
    server.join();
  }

  private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
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

}
