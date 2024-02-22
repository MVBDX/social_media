package com.vue.vueverse.domain.user;

import com.vue.vueverse.domain.user.valueObject.Email;
import com.vue.vueverse.domain.user.valueObject.Password;
import com.vue.vueverse.domain.user.valueObject.PhoneNumber;
import com.vue.vueverse.domain.user.valueObject.Username;
import lombok.Getter;

@Getter
public class User {
    private static long lastAssignedId = 0;
    private final Long id;
    private final Username username;
    private final Password password;
    private final Email email;
    private final PhoneNumber phonenumber;
    private final Gender gender;

    public User(Username username, Password password, Email email, PhoneNumber phonenumber, Gender gender) {
        this.id = generateUniqueId();
        this.phonenumber = phonenumber;
        this.password = password;
        this.gender = gender;
        this.username = username;
        this.email = email;
    }

    private static synchronized long generateUniqueId() {
        return ++lastAssignedId;
    }

    public String getPasswordValue() {
        return password.getPassword();
    }

    public String getUsernameValue() {
        return username.getName();
    }
}
