package com.vue.vueverse.domain.user.valueObject;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

public class Username {
    @Getter
    private final String username;
    private final UserRepository userRepository;

    public Username(String username, UserRepository userRepository) {
        isValidUsername(username);
        this.username = username;
        this.userRepository = userRepository;
    }


    public boolean updateEmail(User user, String username) {

        User currentUser = userRepository.findByUsernameOrEmail(user.getUsername().getUsername(), user.getEmail().getEmail())
                .orElseThrow(() -> new UserException("user doesn't exist "));

        isValidUsername(username);

        if (!Objects.equals(currentUser.getUsername().getUsername(), username))
            return userRepository.save(new User(user.getUsername(), user.getPassword(), user.getEmail(),
                    user.getPhonenumber(), user.getGender()));

        return false;
    }

    private void isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]+$";
        if (!Pattern.matches(regex, username))
            throw new UserException("Username should contain only alphanumeric characters and underscores");
    }

}
