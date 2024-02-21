package com.vue.vueverse.domain.user.valueObject;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

public class Username { //class name?
    @Getter
    private final String name;
    private final UserRepository userRepository;

    public Username(String name, UserRepository userRepository) {
        validate();
        this.name = name;
        this.userRepository = userRepository;
    }


    public boolean updateUsername(User user, String username) {

        User currentUser = userRepository.findByUsernameOrEmail(user.getUsername().getName(), user.getEmail().getEmail())
                .orElseThrow(() -> new UserException("User doesn't exist."));

        validate();

        if (!Objects.equals(currentUser.getUsername().getName(), username))
            return userRepository.save(new User(user.getUsername(), user.getPassword(), user.getEmail(),
                    user.getPhonenumber(), user.getGender()));

        return false;
    }

    private void validate() throws UserException {
        String regex = "^[a-zA-Z0-9_]+$";
        if (!Pattern.matches(regex, this.name))
            throw new UserException("Username should contain only alphanumeric characters and underscores");
    }

}
