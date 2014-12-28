package ggd.webletter.pdf;

import com.lowagie.text.DocumentException;
import ggd.webletter.PdfReader;
import ggd.webletter.model.Closing;
import ggd.webletter.model.Letter;
import ggd.webletter.model.Person;
import ggd.webletter.model.Salutation;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class PdfLetterTest {

    private final String body = "hello world";
    private Salutation salutation = new Salutation("Hi");
    private Closing closing = new Closing("Bye");
    private Person receiver = new Person("receiver", "receiver address");
    private Person sender = new Person("sender", "sender address");
    private Letter letter;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void before() {
        letter = Letter.create(sender, receiver, salutation, closing, body);
    }

    @Test
    public void expectedContentIsInPdf() throws IOException, DocumentException {
        letter.setDate(new LocalDate(2012, 2, 25));

        InputStream pdfStream = new PdfLetter(letter, Locale.FRANCE).getInputStream();

        String pdfTextContent = PdfReader.toString(pdfStream).trim();
        assertThat(pdfTextContent).contains(letter.getSender().getName());
        assertThat(pdfTextContent).contains(letter.getSender().getAddress());
        assertThat(pdfTextContent).contains(letter.getReceiver().getName());
        assertThat(pdfTextContent).contains(letter.getReceiver().getAddress());
        assertThat(pdfTextContent).contains(letter.getSalutation().toString());
        assertThat(pdfTextContent).contains(letter.getBody());
        assertThat(pdfTextContent).contains(letter.getClosing().toString());
        assertThat(pdfTextContent).contains("25 février 2012");
    }

    @Test
    public void canWriteToFile() throws IOException, DocumentException {
        String fileName = temporaryFolder.newFile().getAbsolutePath();

        new PdfLetter(letter).writeToFile(fileName);

        assertThat(PdfReader.toString(new FileInputStream(fileName))).contains(body);
    }
}
