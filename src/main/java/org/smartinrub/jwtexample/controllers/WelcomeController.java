package org.smartinrub.jwtexample.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("index")
public class WelcomeController {

	@GetMapping
	public String welcome(HttpServletRequest request) {
		return "Welcome " + request.getAttribute("username");
	}

}
