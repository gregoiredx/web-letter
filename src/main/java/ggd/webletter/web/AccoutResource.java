package ggd.webletter.web;

import ggd.webletter.model.Account;
import ggd.webletter.model.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Named
@Path(AccoutResource.PATH)
public class AccoutResource {

    public static final String PATH = "/accounts";

    @Autowired
    private AccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @POST
    @Transactional
    public Response create(@FormParam("login") String login, @FormParam("password") String password){
        Account account = new Account(login, passwordEncoder.encode(password));
        repository.persist(account);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{login}")
    @Transactional
    public Response delete(@PathParam("login") String login, @QueryParam("password") String password){
        Account account = repository.load(login);
        if(account == null){
            throw new WebApplicationException("Unknown account " + login, Response.Status.NOT_FOUND);
        }
        if (! passwordEncoder.matches(password, account.getCredentials())){
            throw new WebApplicationException("Invalid password for " + login, Response.Status.FORBIDDEN);
        }
        repository.remove(account);
        return Response.ok().build();
    }
}
