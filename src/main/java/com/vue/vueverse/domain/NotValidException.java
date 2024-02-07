package com.vue.vueverse.domain;

public class NotValidException extends RuntimeException {
    private final String message;

    public NotValidException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
