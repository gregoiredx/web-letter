package ggd.webletter.web;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Named
@Path(LoginResource.PATH)
public class LoginResource {

    public static final String PATH = "/login";

    @GET
    public Response get(){
        return Response.seeOther(UriBuilder.fromResource(LetterFormResource.class).build()).build();
    }
}
