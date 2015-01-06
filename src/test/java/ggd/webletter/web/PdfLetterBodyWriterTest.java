package ggd.webletter.web;

import ggd.webletter.test.LetterFactory;
import ggd.webletter.test.PdfReader;
import ggd.webletter.model.Letter;
import org.joda.time.LocalDate;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class PdfLetterBodyWriterTest {

    @Test
    public void adaptContentToLanguage() throws WebApplicationException, IOException {
        LocalDate date = new LocalDate(2012, 2, 25);

        assertThat(prepareLetter(franceHeader("fr"), date)).contains("février");
        assertThat(prepareLetter(franceHeader("us"), date)).contains("February");
    }

    @Test
    public void defaultsLangageToFrench() throws WebApplicationException, IOException {
        LocalDate date = new LocalDate(2012, 2, 25);

        assertThat(prepareLetter(new MultivaluedHashMap<String, String>(), date)).contains("février");
    }

    private MultivaluedHashMap<String, String> franceHeader(String locale) {
        MultivaluedHashMap<String, String> httpHeaders = new MultivaluedHashMap<String, String>();
        httpHeaders.add("Accept-Language", locale);
        return httpHeaders;
    }

    private String prepareLetter(MultivaluedMap headers, LocalDate date) throws IOException {
        Letter letter = LetterFactory.create();
        letter.setDate(date);
        ByteArrayOutputStream entityStream = new ByteArrayOutputStream();
        new PdfLetterBodyWriter().writeTo(letter, null, null, null, null, headers, entityStream);
        return PdfReader.toString(entityStream);
    }

}
