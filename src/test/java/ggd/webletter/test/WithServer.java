package ggd.webletter.test;

import ggd.webletter.config.WebServer;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.rules.ExternalResource;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.net.URI;

public class WithServer extends ExternalResource {

    private WebServer webServer;

    @Override
    protected void before() throws Throwable {
        System.setProperty("DATABASE_URL", "postgres://test:test@localhost/webletter");
        webServer = WebServer.create();
        webServer.start();
    }

    @Override
    protected void after() {
        webServer.stop();
    }

    public URI getBaseUrl() {
        return webServer.getBaseUrl();
    }

    public WebTarget getTarget() {
        return ClientBuilder.newClient().property(ClientProperties.FOLLOW_REDIRECTS, false).target(getBaseUrl());
    }
}
