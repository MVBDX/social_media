package com.vue.vueverse.domain.user.valueObject;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;
import com.vue.vueverse.domain.user.valueObject.update.EmailUpdater;
import lombok.Getter;

import java.util.regex.Pattern;

public class Email {
    @Getter
    private final String email;

    private final UserRepository userRepository;

    public Email(String email, UserRepository userRepository) {
        validate();
        this.email = email;
        this.userRepository = userRepository;

    }


    public boolean updateEmail(User user, Email newEmail) {

        String email = newEmail.getEmail();
        validate();
        return new EmailUpdater(userRepository).update(user, newEmail);
    }

    public void validate() throws UserException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        boolean isValidEmail = Pattern.matches(emailRegex, email);
        if (!isValidEmail)
            throw new UserException("email not valid");
    }

}
