package org.smartinrub.jwtexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/index")
public class WelcomeController {

    @GetMapping
    public String welcome(HttpServletRequest request) {
        return "Welcome " + request.getAttribute("username");
    }

}
