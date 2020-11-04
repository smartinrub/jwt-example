package org.smartinrub.jwtexample.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.smartinrub.jwtexample.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.smartinrub.jwtexample.utils.SecurityConstants.JWT_EXPIRATION_TIME;

@RestController
@RequestMapping("/token")
public class AuthenticationController {

    private static final String EMAIL = "email@domain.com";
    private static final String PASSWORD = "Password1";
    private static final String USERNAME = "Sergio";

    @Value("${jwt.secret}")
    private String secret;

    @PostMapping
    public String getToken(@RequestBody @Valid User user) {

        String email = user.getEmail();
        String password = user.getPassword();

        if (!EMAIL.equals(email) || !PASSWORD.equals(password)) {
            return "Incorrect Email and/or password!";
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", USERNAME);

        return createToken(email, claims);
    }

    private String createToken(String email, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


}
