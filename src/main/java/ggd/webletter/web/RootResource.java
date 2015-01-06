package ggd.webletter.web;

import ggd.webletter.web.LetterFormResource;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Named
@Path("/")
public class RootResource {

    @GET
    public Response get() {
        return Response.seeOther(UriBuilder.fromResource(LetterFormResource.class).build()).build();
    }

}
