package ggd.webletter.web;

import ggd.webletter.model.Account;
import ggd.webletter.model.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;
import javax.transaction.Transactional;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
}
