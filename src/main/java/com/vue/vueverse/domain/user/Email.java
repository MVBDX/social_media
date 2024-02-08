package com.vue.vueverse.domain.user;

import lombok.Getter;

import java.util.Objects;

public class Email {
    @Getter
    private final String email;
    private final UserRepository userRepository;

    public Email(String email, UserRepository userRepository) {
        RegistrationValidator.isValidEmail(email);
        this.email = email;
        this.userRepository = userRepository;
    }


    public boolean updateEmail(User user, Email email) {

        User currentUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail().getEmail())
                .orElseThrow(() -> new UserException("user doesn't exist "));

        RegistrationValidator.isValidEmail(email.getEmail());

        if (!Objects.equals(currentUser.getEmail().getEmail(), email.getEmail()))
            return userRepository.save(new User(user.getUsername(), user.getPassword(), email,
                    user.getPhonenumber(), user.getGender()));

        return false;
    }

}
