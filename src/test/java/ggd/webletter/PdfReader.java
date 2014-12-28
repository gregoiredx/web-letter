package ggd.webletter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.*;

public class PdfReader {

    public static String toString(InputStream pdfStream) throws IOException {
        PDDocument document = PDDocument.load(pdfStream);
        StringWriter outputStream = new StringWriter();
        new PDFTextStripper().writeText(document, outputStream);
        return outputStream.getBuffer().toString();
    }

    public static String toString(ByteArrayOutputStream entityStream) throws IOException {
        return toString(new ByteArrayInputStream(entityStream.toByteArray()));
    }

}
