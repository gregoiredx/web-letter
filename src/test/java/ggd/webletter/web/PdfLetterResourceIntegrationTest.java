package ggd.webletter.web;

import ggd.webletter.PdfReader;
import ggd.webletter.WithServer;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class PdfLetterResourceIntegrationTest {

    @ClassRule
    public static WithServer server = new WithServer();

    private Response getResponse() {
        Form form = new Form();
        form.param("closing", "Cordialement");
        Entity<Form> entity = Entity.form(form);
        return ClientBuilder.newClient().target(server.getBaseUrl()).path(PdfLetterResource.PATH).request().post(entity);
    }

    @Test
    public void pdfLetterExists() throws Exception {
        int resposeCode = getResponse().getStatus();
        assertThat(resposeCode).isEqualTo(200);
    }

    @Test
    public void pdfLetterHasPdfContentType() throws Exception {
        String contentType = getResponse().getMediaType().toString();
        assertThat(contentType).isEqualTo("application/pdf");
    }

    @Test
    public void canDownloadPdfLetter() throws Exception {
        InputStream responseContent = (InputStream) getResponse().getEntity();
        assertThat(PdfReader.toString(responseContent)).contains("Cordialement");
    }

}
