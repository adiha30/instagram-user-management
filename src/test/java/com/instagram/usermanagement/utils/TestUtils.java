package com.instagram.usermanagement.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.usermanagement.models.User;

public class TestUtils {

    static ObjectMapper objectMapper = new ObjectMapper();

    public static String generateValidUser() throws JsonProcessingException {
        User user = new User();
        user.setUsername("testing");
        user.setPassword("password");

        return objectMapper.writeValueAsString(user);
    }

    public static String generateInvalidUser() {
        return "{ \"invalidField\": \"\", \"anotherInvalidField\": \"\" }";
    }
}
