package ggd.webletter.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import ggd.webletter.model.Letter;
import ggd.webletter.model.Person;

import java.io.*;
import java.util.Locale;

public class PdfLetter {

    private final Letter letter;
    private PdfWriter pdfWriter;
    private Document document;
    private final Locale locale;

    public PdfLetter(Letter letter) {
        this(letter, Locale.getDefault());
    }

    public PdfLetter(Letter letter, Locale locale) {
        this.letter = letter;
        this.locale = locale;
    }

    public InputStream getInputStream() throws DocumentException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeTo(out);
        return new ByteArrayInputStream(out.toByteArray());
    }

    public void writeTo(OutputStream outputStream) throws DocumentException, IOException {
        document = new Document();
        pdfWriter = PdfWriter.getInstance(document, outputStream);
        document.open();
        addSender();
        addReceiver();
        addDate();
        addSalutation();
        addBody();
        addClosing();
        addSignature();
        document.close();
    }

    private void addSender() throws DocumentException {
        document.add(person(letter.getSender()));
    }

    private void addReceiver() throws DocumentException, IOException {
        addToTopRight(350, 40, person(letter.getReceiver()));
    }

    private void addDate() throws DocumentException {
        Paragraph paragraph = new Paragraph(letter.getDate().toString("EEEE d MMMM YYYY,", locale));
        paragraph.setIndentationLeft(300);
        paragraph.setSpacingBefore(150);
        paragraph.setSpacingAfter(40);
        document.add(paragraph);
    }

    private void addSalutation() throws DocumentException {
        Paragraph paragraph = new Paragraph(letter.getSalutation().toString());
        paragraph.setIndentationLeft(40);
        paragraph.setSpacingAfter(40);
        document.add(paragraph);
    }

    private void addBody() throws DocumentException {
        String[] paragraphs = letter.getBody().split("\n\n");
        for (String content : paragraphs) {
            Paragraph paragraph = new Paragraph(content);
            paragraph.setIndentationLeft(40);
            paragraph.setIndentationRight(40);
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.setFirstLineIndent(20);
            document.add(paragraph);
        }
    }

    private void addClosing() throws DocumentException {
        Paragraph paragraph = new Paragraph(letter.getClosing().toString());
        paragraph.setIndentationLeft(60);
        paragraph.setSpacingBefore(40);
        document.add(paragraph);
    }

    private void addSignature() throws DocumentException {
        Paragraph paragraph = new Paragraph(letter.getSender().getName());
        paragraph.setIndentationLeft(300);
        paragraph.setSpacingBefore(40);
        document.add(paragraph);
    }

    private void addToTopRight(float paddingLeft, float paddingTop, Paragraph paragraph) throws DocumentException {
        PdfContentByte directContent = pdfWriter.getDirectContent();
        ColumnText ct = new ColumnText(directContent);
        ct.setSimpleColumn(paragraph, paddingLeft, document.bottom(), document.right(), document.top() - paddingTop,
                20, Element.ALIGN_LEFT);
        ct.go();
    }

    private Paragraph person(Person person) {
        return new Paragraph(person == null ? "" : person.getName() + "\n" + person.getAddress());
    }

    public void writeToFile(String fileName) throws DocumentException, FileNotFoundException, IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        writeTo(fileOutputStream);
        fileOutputStream.close();
    }

}
