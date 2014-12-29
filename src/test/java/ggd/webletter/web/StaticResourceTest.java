package ggd.webletter.web;


import ggd.webletter.WithServer;
import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class StaticResourceTest {

    @Rule
    public WithServer server = new WithServer();

    @Test
    public void canGet() {
        Response response = server.getTarget().path("static").path("hello.html").request().get();

        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.readEntity(String.class)).isEqualTo("hello");
    }

}