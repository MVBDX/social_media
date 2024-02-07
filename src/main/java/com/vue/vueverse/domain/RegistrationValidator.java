package com.vue.vueverse.domain;

import java.util.regex.Pattern;

public class RegistrationValidator {


    public static void isValidUsername(String username) {
        // Username should contain only alphanumeric characters and underscores
        boolean isValidUsername = Pattern.matches("^[a-zA-Z0-9_]+$", username);
        if (!isValidUsername)
            throw new NotValidException("username not valid");
    }


    public static void isValidEmail(String email) {
        // Simple email validation regex (for illustration purposes)
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValidEmail = Pattern.matches(emailRegex, email);
        if (!isValidEmail)
            throw new NotValidException("email not valid");
    }
}
