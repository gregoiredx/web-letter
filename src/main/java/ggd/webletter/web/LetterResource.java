package ggd.webletter.web;

import ggd.webletter.model.Letter;
import ggd.webletter.model.repository.AccountRepository;
import ggd.webletter.model.repository.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

@Path(LetterResource.PATH)
@Named
public class LetterResource {

    public static final String PATH = "/letters";

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private AccountRepository accountRepository;

    @POST
    @Transactional
    public Response create(@Context UriInfo uriInfo,
                           @Context SecurityContext securityContext,
                           @FormParam("sender_name") @DefaultValue("") String senderName,
                           @FormParam("sender_address") @DefaultValue("") String senderAddress,
                           @FormParam("receiver_name") @DefaultValue("") String receiverName,
                           @FormParam("receiver_address") @DefaultValue("") String receiverAddress,
                           @FormParam("salutation") @DefaultValue("") String salutation,
                           @FormParam("body") @DefaultValue("") String body,
                           @FormParam("closing") @DefaultValue("") String closing) {
        Letter letter = Letter.createFromParams(senderName, senderAddress, receiverName, receiverAddress, salutation, body, closing);
        if(securityContext.getUserPrincipal() != null){
            letter.setAccount(accountRepository.load(securityContext.getUserPrincipal().getName()));
        }
        letterRepository.persist(letter);
        return Response.created(uriInfo.getBaseUriBuilder().path(LetterResource.class).path(letter.getId()).build()).build();
    }
}
