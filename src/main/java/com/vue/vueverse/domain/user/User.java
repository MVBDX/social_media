package com.vue.vueverse.domain.user;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import lombok.Getter;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Getter
public class User {
    private static long lastAssignedId = 0;
    private final Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private final Gender gender;

    public User(String username, String password, String email, String phoneNumber, Gender gender) {
        this.id = generateUniqueId();
        setPhoneNumber(phoneNumber);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        this.gender = gender;
    }

    private static synchronized long generateUniqueId() {
        return ++lastAssignedId;
    }


    public User updateUser(User user) {
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setPhoneNumber(user.getPhoneNumber());
        return this;
    }


    private void setUsername(String username) {
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{2,}$";
        if (Objects.nonNull(username) && !Pattern.matches(regex, username))
            throw new UserException("Username should contain only alphanumeric characters and underscores");
        this.username = username;
    }

    private void setPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        if (Objects.nonNull(password) && !Pattern.matches(passwordRegex, password)) {
            throw new UserException("Password should contain at least 8 characters, " +
                    "including at least one uppercase letter, one lowercase letter, and one digit");
        }
        this.password = password;
    }

    private void setEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (Objects.nonNull(email) && !Pattern.matches(emailRegex, email))
            throw new UserException("email not valid");

        this.email = email;
    }

    private void setPhoneNumber(String phoneNumber) {

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            if (Objects.nonNull(phoneNumber) &&
                    !phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumber, "ZZ"))) {
                throw new UserException("Invalid phone number");
            }
        } catch (NumberParseException exception) {
            throw new UserException(exception.getMessage());
        }
        this.phoneNumber = phoneNumber;
    }


}
