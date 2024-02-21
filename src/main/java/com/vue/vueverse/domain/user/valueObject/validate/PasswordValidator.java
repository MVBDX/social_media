package com.vue.vueverse.domain.user.valueObject.validate;

import com.vue.vueverse.domain.user.UserException;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator {
    @Override
    public void validate(String password) throws UserException {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!Pattern.matches(passwordRegex, password)) {
            throw new UserException("Password should contain at least 8 characters, including at least one uppercase letter, one lowercase letter, and one digit");
        }
    }
}
