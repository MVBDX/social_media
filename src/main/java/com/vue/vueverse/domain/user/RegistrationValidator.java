package com.vue.vueverse.domain.user;

import java.util.regex.Pattern;

public class RegistrationValidator {

    public static void isValidUsername(String username) {
        if (username != null) {
            String regex = "^[a-zA-Z0-9_]+$";
            boolean isValidUsername = Pattern.matches(regex, username);
            if (!isValidUsername)
                throw new UserException("Username should contain only alphanumeric characters and underscores");
        }
    }


    public static void isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValidEmail = Pattern.matches(emailRegex, email);
        if (!isValidEmail)
            throw new UserException("email not valid");
    }
}
