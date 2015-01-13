package ggd.webletter.web;

import ggd.webletter.model.Letter;

import javax.inject.Named;
import javax.ws.rs.*;

@Path(PdfLetterResource.PATH)
@Named
public class PdfLetterResource {

    public static final String PATH = "/pdfLetter";

    @POST
    @Produces("application/pdf")
    public Letter createPdf(@FormParam("sender_name") @DefaultValue("") String senderName,
                            @FormParam("sender_address") @DefaultValue("") String senderAddress,
                            @FormParam("receiver_name") @DefaultValue("") String receiverName,
                            @FormParam("receiver_address") @DefaultValue("") String receiverAddress,
                            @FormParam("salutation") @DefaultValue("") String salutation,
                            @FormParam("body") @DefaultValue("") String body,
                            @FormParam("closing") @DefaultValue("") String closing) {
        return Letter.createFromParams(senderName, senderAddress, receiverName, receiverAddress, salutation, body, closing);
    }

}
