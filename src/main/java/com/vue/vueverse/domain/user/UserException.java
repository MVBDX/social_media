package com.vue.vueverse.domain.user;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private final String message;

    public UserException(String message) {
        this.message = message;
    }


}
