package com.vue.vueverse.domain.user.valueObject.validate;

import com.vue.vueverse.domain.user.UserException;

import java.util.regex.Pattern;

public class EamilValidator implements Validator {
    private final String email;

    public EamilValidator(String email) {
        this.email = email;
    }

    @Override
    public void validate() throws UserException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValidEmail = Pattern.matches(emailRegex, email);
        if (!isValidEmail)
            throw new UserException("email not valid");

    }
}
