package com.instagram.usermanagement.configurations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
public class SecurityConfigTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Test if the /users/register endpoint is accessible without authentication")
    public void testRegisterEndpointAccessible() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/users/register"))
                .andExpect(status().isOk());
    }
}
