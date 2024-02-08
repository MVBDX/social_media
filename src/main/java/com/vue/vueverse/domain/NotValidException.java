package com.vue.vueverse.domain;

import lombok.Getter;

@Getter
public class NotValidException extends RuntimeException {
    private final String message;

    public NotValidException(String message) {
        this.message = message;
    }


}
