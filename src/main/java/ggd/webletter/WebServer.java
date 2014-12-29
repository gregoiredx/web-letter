package ggd.webletter;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.DispatcherType;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.EnumSet;

public class WebServer {

    private final Server server;

    private WebServer(Server server) {
        this.server = server;
    }

    public static WebServer create() {
        return create(null);
    }

    public static WebServer create(Integer port) {
        Server server = port == null ? new Server(0) : new Server(port);
        HandlerList handlers = new HandlerList();
        handlers.addHandler(staticResourcesHandler());
        handlers.addHandler(servletContextHandler(getContext()));
        server.setHandler(handlers);
        return new WebServer(server);
    }

    private static Handler staticResourcesHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});
        resourceHandler.setBaseResource(Resource.newClassPathResource("static"));
        ContextHandler contextHandler = new ContextHandler("/static");
        contextHandler.setHandler(resourceHandler);
        return contextHandler;
    }

    private static ServletContextHandler servletContextHandler(WebApplicationContext context) {
        ServletContextHandler contextHandler = new ServletContextHandler();
        ServletHolder servletHolder = new ServletHolder(new JerseySpringServletContainer(context));
        contextHandler.addServlet(servletHolder, "/*");
        contextHandler.addEventListener(new ContextLoaderListener(context));
        return contextHandler;
    }

    private static WebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan(Main.class.getPackage().getName());
        context.refresh();
        return context;
    }

    public void startAndWait() {
        start();
        join();
    }

    public void start() {
        tryTo(Server::start);
    }

    private void join() {
        tryTo(Server::join);
    }

    public void stop() {
        tryTo(Server::stop);
    }

    public URI getBaseUrl() {
        URI uri = server.getURI();
        try {
            return new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), null, null, null);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Could not build server URI", e);
        }
    }

    @FunctionalInterface
    private static interface ServerAction {
        void perform(Server server) throws Exception;
    }

    private void tryTo(ServerAction serverAction) {
        try {
            serverAction.perform(server);
        } catch (Exception e) {
            throw new RuntimeException("Could not perform action on server", e);
        }
    }

}
