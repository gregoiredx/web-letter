package ggd.webletter;

import org.junit.rules.ExternalResource;

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
}
