package ggd.webletter.web;


import ggd.webletter.WithServer;
import org.junit.ClassRule;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class LetterFormResourceIntegrationTest {

    @ClassRule
    public static WithServer server = new WithServer();

    @Test
    public void canGet() {
        Response response = ClientBuilder.newClient().target(server.getBaseUrl()).path(LetterFormResource.PATH).request().get();

        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
        assertThat(response.readEntity(String.class)).contains("form name=\"letterForm\"");
    }

}