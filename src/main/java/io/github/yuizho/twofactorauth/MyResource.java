package io.github.yuizho.twofactorauth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import java.util.concurrent.ExecutionException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    @GET
    @Path("credential")
    @Produces(MediaType.TEXT_HTML)
    public String getCredenstial(@NotNull @QueryParam("userName") String userName)
            throws InterruptedException, ExecutionException {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials(userName);
        return String.format("<html><body><h1>otpauth://totp/%s?secret=%s&issuer=two-factor-auth-sample</h1></body></html>",
                userName, key.getKey());
    }

    @GET
    @Path("auth")
    @Produces(MediaType.TEXT_HTML)
    public String auth(@NotNull @QueryParam("userName") String userName,
            @NotNull @QueryParam("code") int code)
            throws InterruptedException, ExecutionException {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isCodeValid = gAuth.authorizeUser(userName, code);
        return "<html><body><h1>result: " + (isCodeValid ? "success" : "authentication failed") + "</h1></body></html>";
    }
}
