package com.vue.vueverse.domain;

import java.util.regex.Pattern;

public class Password {
    private final String password;

    public Password(String password) {
        isValidPassword(password);
        this.password = password;
    }

    private void isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        boolean isValidPassword = Pattern.matches(passwordRegex, password);
        if (!isValidPassword)
            throw new NotValidException("Password should contain at least 8 characters, including at least " +
                    "one uppercase letter, one lowercase letter, and one digit");

    }

    //todo add change password
    public String getPassword() {
        return password;
    }
}
