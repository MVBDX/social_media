package com.vue.vueverse.presentation.dto;

import lombok.Data;

@Data
public class UserResponse {
    private final Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private final GenderResponse gender;

}
