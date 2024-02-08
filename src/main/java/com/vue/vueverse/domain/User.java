package com.vue.vueverse.domain;

import lombok.Getter;

@Getter
public class User {
    private static long lastAssignedId = 0;
    private final Long id;
    private final String username;
    private final Password password;
    private final String email;
    private final PhoneNumber phonenumber;
    private final Gender gender;

    public User(String username, Password password, String email, PhoneNumber phonenumber, Gender gender) {
        this.id = generateUniqueId();
        RegistrationValidator.isValidUsername(username);
        RegistrationValidator.isValidEmail(email);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.gender = gender;
    }


    private synchronized static long generateUniqueId() {
        return ++lastAssignedId;
    }

}
