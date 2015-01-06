package ggd.webletter.web;

import ggd.webletter.test.WithServer;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.Status.Family.SUCCESSFUL;
import static javax.ws.rs.core.Response.Status.Family.familyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD;
import static org.glassfish.jersey.client.authentication.HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME;

public class LoginResourceTest {

    public static final String LOGIN = "aUser";
    public static final String PASSWORD = "aPassword";
    @Rule
    public WithServer withServer = new WithServer();

    @Before
    public void before(){
        removeUser();
        createUser();
    }

    private void removeUser() {
        Response response = withServer.getTarget().path(AccoutResource.PATH).path(LOGIN).queryParam("password", PASSWORD).request().delete();
        assertThat(response.getStatus()).isIn(OK.getStatusCode(), NOT_FOUND.getStatusCode());
    }

    private void createUser() {
        Form form = new Form();
        form.param("login", LOGIN);
        form.param("password", PASSWORD);
        Response response = withServer.getTarget().path(AccoutResource.PATH).request().post(Entity.form(form));
        assertThat(response.getStatusInfo().getFamily()).isEqualTo(SUCCESSFUL);
    }

    @Test
    public void canLogin(){
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.universal(LOGIN, PASSWORD);

        Response response = withServer.getTarget().register(feature).path(LoginResource.PATH).request().get();

        assertThat(response.getStatus()).isEqualTo(SEE_OTHER.getStatusCode());
    }

    @Test
    public void checkCredentials(){
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.universal(LOGIN, "aWrongPassword");

        Response response = withServer.getTarget().register(feature).path(LoginResource.PATH).request().get();

        assertThat(response.getStatus()).isEqualTo(UNAUTHORIZED.getStatusCode());
    }

}
