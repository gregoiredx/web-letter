package ggd.webletter.web;

import com.lowagie.text.DocumentException;
import ggd.webletter.model.Letter;
import ggd.webletter.pdf.PdfLetter;

import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Locale;

@Provider
@Named
public class PdfLetterBodyWriter implements MessageBodyWriter<Letter> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type.equals(Letter.class) && mediaType.toString().equals("application/pdf");
    }

    @Override
    public long getSize(Letter t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Letter letter, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException,
            WebApplicationException {
        try {
            String language = getLanguage(httpHeaders);
            new PdfLetter(letter, new Locale(language)).writeTo(entityStream);
        } catch (DocumentException e) {
            throw new WebApplicationException(e);
        }
    }

    private String getLanguage(MultivaluedMap<String, Object> httpHeaders) {
        if (httpHeaders.containsKey("Accept-Language")) {
            return (String) httpHeaders.getFirst("Accept-Language");

        }
        return "fr";
    }

}
