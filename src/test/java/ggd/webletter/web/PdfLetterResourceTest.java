package ggd.webletter.web;

import ggd.webletter.model.Letter;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PdfLetterResourceTest {

    @Test
    public void createLetterWithGivenParameters() {
        String senderName = "Joe Lafritte";
        String senderAddress = "Ici où là\33001 Pas Loin";
        String salutation = "Madame,";
        String body = "letter content\n\rbla";
        String receiverName = "receiver";
        String receiverAddress = "receiver adress\nreceiver city";
        String closing = "Cordialement";

        Letter letter = new PdfLetterResource().createPdf(senderName, senderAddress, receiverName, receiverAddress,
                salutation, body, closing);

        assertThat(letter.getSender().getName()).isEqualTo(senderName);
        assertThat(letter.getSender().getAddress()).isEqualTo(senderAddress);
        assertThat(letter.getReceiver().getName()).isEqualTo(receiverName);
        assertThat(letter.getReceiver().getAddress()).isEqualTo(receiverAddress);
        assertThat(letter.getSalutation().toString()).isEqualTo(salutation);
        assertThat(letter.getBody()).isEqualTo("letter content\nbla");
        assertThat(letter.getClosing().toString()).isEqualTo(closing);
    }
}
