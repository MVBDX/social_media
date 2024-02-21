package com.vue.vueverse.domain.user.valueObject;


import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;

import java.util.Objects;
import java.util.regex.Pattern;


public class Password {

    private final String password;
    private final UserRepository userRepository;

    public Password(String password, UserRepository userRepository) {
        validate();
        this.password = password;
        this.userRepository = userRepository;
    }

    public boolean updatePassword(User user, String newPassword) {

        User currentUser = userRepository.findByUsernameOrEmail(user.getUsername().getName(), user.getEmail().getEmail())
                .orElseThrow(() -> new UserException("User doesn't exist."));

        var oldPassword = currentUser.getPassword().password;

        validate();

        if (!Objects.equals(oldPassword, newPassword))
            return userRepository.save(new User(user.getUsername(), new Password(newPassword, userRepository),
                    user.getEmail(), user.getPhonenumber(), user.getGender()));

        return false;
    }

    public void validate() throws UserException {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (!Pattern.matches(passwordRegex, password)) {
            throw new UserException("Password should contain at least 8 characters, " +
                    "including at least one uppercase letter, one lowercase letter, and one digit");
        }
    }
}
