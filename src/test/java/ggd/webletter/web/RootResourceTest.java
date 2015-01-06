package ggd.webletter.web;


import ggd.webletter.test.WithServer;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class RootResourceTest {

    @Rule
    public WithServer server = new WithServer();

    @Test
    public void canGet() {
        Response response = server.getTarget().property(ClientProperties.FOLLOW_REDIRECTS, false).request().get();

        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_SEE_OTHER);
    }
}