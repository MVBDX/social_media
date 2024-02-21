package com.vue.vueverse.domain.user.valueObject.update;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;
import com.vue.vueverse.domain.user.valueObject.Email;
import com.vue.vueverse.domain.user.valueObject.validate.Validator;

import java.util.Objects;

public class EmailUpdater implements Updater<Email> {
    private final UserRepository userRepository;
    private final Validator validator;

    public EmailUpdater(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public boolean update(User user, Email email) {

        User currentUser = userRepository.findByUsernameOrEmail(user.getUsername().getUsername(), user.getEmail().getEmail())
                .orElseThrow(() -> new UserException("user doesn't exist "));


        if (!Objects.equals(currentUser.getEmail().getEmail(), email.getEmail()))
            return userRepository.save(new User(user.getUsername(), user.getPassword(), email,
                    user.getPhonenumber(), user.getGender()));

        return false;
    }
}

