package com.vue.vueverse.domain;

import java.util.regex.Pattern;

public class RegistrationValidator {

    public static void isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]+$";
        boolean isValidUsername = Pattern.matches(regex, username);
        if (!isValidUsername)
            throw new NotValidException("Username should contain only alphanumeric characters and underscores");
    }


    public static void isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValidEmail = Pattern.matches(emailRegex, email);
        if (!isValidEmail)
            throw new NotValidException("email not valid");
    }
}
