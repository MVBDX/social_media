package com.vue.vueverse.domain.user;

import lombok.Getter;

@Getter
public class User {
    private static long lastAssignedId = 0;
    private final Long id;
    private final String username;
    private final Password password;
    private final Email email;
    private final PhoneNumber phonenumber;
    private final Gender gender;

    public User(String username, Password password, Email email, PhoneNumber phonenumber, Gender gender) {
        RegistrationValidator.isValidUsername(username);
        RegistrationValidator.isValidEmail(email.getEmail());
        this.id = generateUniqueId();
        this.phonenumber = phonenumber;
        this.password = password;
        this.gender = gender;
        this.username = username;
        this.email = email;
    }


    private synchronized static long generateUniqueId() {
        return ++lastAssignedId;
    }

}
