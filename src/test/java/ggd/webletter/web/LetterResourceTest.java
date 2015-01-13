package ggd.webletter.web;

import ggd.webletter.test.WithServer;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.io.InputStream;

import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;
import static org.assertj.core.api.Assertions.assertThat;


public class LetterResourceTest {

    @ClassRule
    public static WithServer server = new WithServer();

    @Test
    public void getResponse() {
        Form form = new Form();
        form.param("closing", "Cordialement");
        Entity<Form> entity = Entity.form(form);

        Response response = server.getTarget().path(LetterResource.PATH).request().post(entity);

        assertThat(response.getStatusInfo().getFamily()).isEqualTo(SUCCESSFUL);
    }

}
