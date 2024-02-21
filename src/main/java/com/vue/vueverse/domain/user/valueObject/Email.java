package com.vue.vueverse.domain.user.valueObject;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;
import com.vue.vueverse.domain.user.valueObject.update.EmailUpdater;
import com.vue.vueverse.domain.user.valueObject.update.Updater;
import com.vue.vueverse.domain.user.valueObject.validate.EamilValidator;
import com.vue.vueverse.domain.user.valueObject.validate.Validator;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    @Getter
    private final String email;

    private final UserRepository userRepository;

    public Email(String email, UserRepository userRepository) {
        this.email = email;
        this.userRepository = userRepository;

    }


    public boolean updateEmail(User user, Email newEmail) {

        String email = newEmail.getEmail();
        var validator = new EamilValidator(email);
        return new EmailUpdater(userRepository, validator).update(user, newEmail);
    }

}
