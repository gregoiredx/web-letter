package ggd.webletter;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Named
@Path("/")
public class OkResource {

    @GET
    public String get(){
        return "ok";
    }

}
