package org.smartinrub.jwtexample.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTokenWhenInvalidUserShouldReturnBadRequest() throws Exception {

        this.mockMvc.perform(post("/token").contentType(MediaType.APPLICATION_JSON)
                .content("{\n\"email\": \"test\",\n\"password\": \"test\"\n}")).andExpect(status().isBadRequest());
    }

    @Test
    void getTokenWhenValidUserShouldReturnJwtToken() throws Exception {

        this.mockMvc.perform(post("/token").contentType(MediaType.APPLICATION_JSON)
                .content("{\n\"email\": \"test@gmail.com\",\n\"password\": \"test\"\n}")).andExpect(status().isOk())
                .andExpect(content().string(any(String.class)));
    }
}
