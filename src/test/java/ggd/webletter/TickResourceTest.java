package ggd.webletter;


import org.junit.Rule;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class TickResourceTest {

    @Rule
    public WithServer server = new WithServer();

    @Test
    public void canGet() {
        Response response = server.getTarget().path(TickResource.PATH).request().get();

        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.readEntity(String.class)).contains("ticks");
    }
}