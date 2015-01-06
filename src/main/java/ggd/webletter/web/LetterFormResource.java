package ggd.webletter.web;

import com.google.common.collect.Maps;
import org.glassfish.jersey.server.mvc.Template;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

@Named
@Path(LetterFormResource.PATH)
public class LetterFormResource {

    public static final String PATH = "/letterForm";

    @GET
    @Template(name = "/letterForm")
    public Map<String, Object> get(@Context UriInfo uriInfo, @Context SecurityContext securityContext) {
        Map<String, Object> context = Maps.newHashMap();
        context.put("userPrincipal", securityContext.getUserPrincipal() == null ? null : securityContext.getUserPrincipal().getName());
        context.put("staticUrl", "/static");
        context.put("pdfLetterUrl", uriInfo.getBaseUriBuilder().path(PdfLetterResource.class).build());
        return context;
    }
}
