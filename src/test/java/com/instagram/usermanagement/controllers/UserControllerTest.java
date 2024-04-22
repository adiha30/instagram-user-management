package com.instagram.usermanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.usermanagement.configurations.WebSecurityConfig;
import com.instagram.usermanagement.models.User;
import com.instagram.usermanagement.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.instagram.usermanagement.utils.TestUtils.generateInvalidUser;
import static com.instagram.usermanagement.utils.TestUtils.generateValidUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {WebSecurityConfig.class, UserController.class})
public class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Test valid registration")
    void testValidRegistration() throws Exception {
        String validUserForRequest = generateValidUser();

        when(userService.saveUser(any())).thenReturn(new User());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/register")
                        .content(validUserForRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test invalid path")
    void testInvalidPath() throws Exception {
        String invalidUserForRequest = generateInvalidUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/invalid")
                        .content(invalidUserForRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Test invalid user")
    void testInvalidUser() throws Exception {
        String invalidUserForRequest = generateInvalidUser();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users/register")
                        .content(invalidUserForRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isBadRequest());
    }
}