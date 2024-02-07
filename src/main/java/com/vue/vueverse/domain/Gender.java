package com.vue.vueverse.domain;

public enum Gender {
    FEMALE(1), MALE(2), OTHER(3);
    private final int type;

    Gender(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
