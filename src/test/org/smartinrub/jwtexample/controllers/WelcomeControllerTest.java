package org.smartinrub.jwtexample.controllers;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.smartinrub.jwtexample.utils.SecurityConstants.JWT_EXPIRATION_TIME;
import static org.smartinrub.jwtexample.utils.SecurityConstants.JWT_SECRET;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WelcomeControllerTest {

    private static final String WELCOME_ENDPOINT = "/index";

    @Autowired
    private MockMvc mockMvc;

    @Test(expected = ServletException.class)
    public void welcomeWhenBearerTokenNotProvidedShouldThrowServletException() throws Exception {
        mockMvc.perform(get(WELCOME_ENDPOINT));
    }

    @Test(expected = JwtException.class)
    public void welcomeWhenBearerTokenProvidedButInvalidShouldThrowJwtException() throws Exception {
        mockMvc.perform(get(WELCOME_ENDPOINT).header("Authorization", "Bearer invalidjwttoken"));
    }

    @Test
    public void welcomeWhenBearerTokenProvidedAndValidShouldReturnWelcomeMessage() throws Exception {
        String userName = "test";
        String email = "test@test.com";

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userName);
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();

        mockMvc.perform(get(WELCOME_ENDPOINT).header("Authorization", "Bearer " + jwt))
                .andExpect(status().isOk()).andExpect(content().string(containsString("Welcome " + userName)));
    }

}
