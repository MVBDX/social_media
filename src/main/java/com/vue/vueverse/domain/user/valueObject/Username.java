package com.vue.vueverse.domain.user.valueObject;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

public class Username {
    @Getter
    private final String name;
    private final UserRepository userRepository;

    public Username(String name, UserRepository userRepository) {
        this.name = name;
        validate();
        this.userRepository = userRepository;
    }


    public boolean updateUsername(User user, String username) {

        User currentUser = userRepository.findByUsernameOrEmail(user.getUsername().getName(), user.getEmail().getEmail())
                .orElseThrow(() -> new UserException("User doesn't exist."));


        if (Objects.equals(currentUser.getUsernameValue(), username))
            throw new UserException("you should Enter new username");

        validate();

        return userRepository.save(new User(user.getUsername(), user.getPassword(), user.getEmail(),
                user.getPhonenumber(), user.getGender()));

    }

    private void validate() throws UserException {
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{2,}$";
        if (!Pattern.matches(regex, this.name))
            throw new UserException("Username should contain only alphanumeric characters and underscores");
    }

}
