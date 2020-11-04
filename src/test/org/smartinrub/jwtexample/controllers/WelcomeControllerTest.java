package org.smartinrub.jwtexample.controllers;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class WelcomeControllerTest {

    private static final String WELCOME_ENDPOINT = "/index";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void welcomeWhenBearerTokenNotProvidedShouldThrowServletException() {
        assertThatThrownBy(() -> mockMvc.perform(get(WELCOME_ENDPOINT)))
                .isInstanceOf(ServletException.class);

    }

    @Test
    void welcomeWhenBearerTokenProvidedButInvalidShouldThrowJwtException() {
        assertThatThrownBy(() -> mockMvc.perform(get(WELCOME_ENDPOINT)
                .header("Authorization", "Bearer invalidjwttoken")))
                .isInstanceOf(JwtException.class);

    }

    @Test
    void welcomeWhenBearerTokenProvidedAndValidShouldReturnWelcomeMessage() throws Exception {
        String userName = "test";
        String email = "test@test.com";

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userName);
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 30_000L))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        mockMvc.perform(get(WELCOME_ENDPOINT).header("Authorization", "Bearer " + jwt))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome " + userName)));
    }

}
