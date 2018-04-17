package org.smartinrub.jwtexample.controllers;

import static org.smartinrub.jwtexample.utils.SecurtityConstants.JWT_EXPIRATION_TIME;
import static org.smartinrub.jwtexample.utils.SecurtityConstants.JWT_SECRET;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.smartinrub.jwtexample.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController("/token")
public class AuthenticationController {

	private static final String EMAIL = "email@domain.com";
	private static final String PASSWORD = "Password1";
	private static final String USERNAME = "Sergio";

	@PostMapping
	public String getToken(@RequestBody @Valid User user) {

		String email = user.getEmail();
		String password = user.getPassword();

		if (!EMAIL.equals(email) || !PASSWORD.equals(password)) {
			return "Incorrect Email and/or password!";
		}

		Map<String, Object> claims = new HashMap<>();
		claims.put("username", USERNAME);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(email).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET)
				.compact();
	}

}
