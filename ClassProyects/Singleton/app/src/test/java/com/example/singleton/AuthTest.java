package com.example.singleton;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

public class AuthTest {

    static Usuario create() {
        Random random = new Random();
        String id = String.valueOf(random.nextLong());
        return new Usuario(id, "Mario", "cc@gmail.com");
    }

    @Test
    public void testUser() {
        Usuario usuario = AuthTest.create();
        Assert.assertEquals(usuario.getName(), "Mario");
        Assert.assertEquals(usuario.getEmail(), "cc@gmail.com");
        Assert.assertNull(usuario.authToken);
    }

    @Test
    public void testTokenExpired() {
        Usuario usuario = AuthTest.create();
        Date expiration = new Date(System.currentTimeMillis() - 10);
        AuthToken authToken = new AuthToken(String.valueOf(new Random().nextLong()), expiration);
        usuario.setAuthToken(authToken);
        Boolean isLogged = Authenticator.getInstance().isLogged(usuario);
        Assert.assertFalse(isLogged);
    }

    @Test
    public void testTokenNotExpired() {
        Usuario usuario = AuthTest.create();
        Authenticator.getInstance().logIn(usuario);
        Assert.assertNotNull(usuario.authToken);
    }

    @Test
    public void testTokenSuccess() {
        Usuario usuario = AuthTest.create();
        Authenticator.getInstance().logIn(usuario);
        Assert.assertNotNull(usuario.authToken);
    }

    @Test
    public void testUserIsLogged() {
        Usuario usuario = AuthTest.create();
        Authenticator.getInstance().logIn(usuario);
        Boolean isLogged = Authenticator.getInstance().isLogged(usuario);
        Assert.assertTrue(isLogged);
    }

    @Test
    public void testUserIsNotLogged() {
        Usuario usuario = AuthTest.create();
        Assert.assertFalse(Authenticator.getInstance().isLogged(usuario));
    }
}
