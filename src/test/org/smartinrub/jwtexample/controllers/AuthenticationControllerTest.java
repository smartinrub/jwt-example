package org.smartinrub.jwtexample.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTokenWhenInvalidUserShouldReturnBadRequest() throws Exception {

        this.mockMvc.perform(post("/token").contentType(MediaType.APPLICATION_JSON)
                .content("{\n\"email\": \"test\",\n\"password\": \"test\"\n}")).andExpect(status().isBadRequest());
    }

    @Test
    public void getTokenWhenValidUserShouldReturnJwtToken() throws Exception {

        this.mockMvc.perform(post("/token").contentType(MediaType.APPLICATION_JSON)
                .content("{\n\"email\": \"test@gmail.com\",\n\"password\": \"test\"\n}")).andExpect(status().isOk())
                .andExpect(content().string(any(String.class)));
    }
}
