package ggd.webletter;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.net.URI;

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
        server.setHandler(servletContextHandler(getContext()));
        return new WebServer(server);
    }

    private static ServletContextHandler servletContextHandler(WebApplicationContext context) {
        ServletContextHandler contextHandler = new ServletContextHandler();
        ServletHolder servletHolder = new ServletHolder(new JerseySpringServletContainer(context));
        contextHandler.addServlet(servletHolder, "/*");
        contextHandler.addEventListener(new ContextLoaderListener(context));
        //    contextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
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
        return server.getURI();
//        int port = ((ServerConnector) server.getConnectors()[0]).getLocalPort();
//        return URI.create("http://localhost:" + port);
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
