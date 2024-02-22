package com.vue.vueverse.presentation.dto;

import lombok.Getter;

@Getter
public enum GenderResponse {
    FEMALE(1),

    MALE(2),

    OTHER(3);

    private final int type;

    GenderResponse(int type) {
        this.type = type;
    }
}
