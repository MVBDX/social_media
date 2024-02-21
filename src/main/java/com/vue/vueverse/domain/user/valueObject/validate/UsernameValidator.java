package com.vue.vueverse.domain.user.valueObject.validate;

import com.vue.vueverse.domain.user.UserException;

import java.util.regex.Pattern;

public class UsernameValidator implements Validator {
    @Override
    public void validate(String username) throws UserException {
        String regex = "^[a-zA-Z0-9_]+$";
        if (!Pattern.matches(regex, username)) {
            throw new UserException("Username should contain only alphanumeric characters and underscores");
        }
    }
}
