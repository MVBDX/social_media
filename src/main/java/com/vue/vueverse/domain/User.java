package com.vue.vueverse.domain;

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

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public PhoneNumber getPhonenumber() {
        return phonenumber;
    }

    public Gender getGender() {
        return gender;
    }
}
