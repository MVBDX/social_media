package com.vue.vueverse.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenderShould {
    @Test
    public void returnCorrectType() {
        assertEquals(1, Gender.FEMALE.getType());
        assertEquals(2, Gender.MALE.getType());
        assertEquals(3, Gender.OTHER.getType());
    }


    @Test
    public void notBeNull() {
        assertNotNull(Gender.FEMALE);
        assertNotNull(Gender.MALE);
        assertNotNull(Gender.OTHER);
    }

    @Test
    public void haveUniqueTypes() {
        assertNotEquals(Gender.FEMALE.getType(), Gender.MALE.getType());
        assertNotEquals(Gender.FEMALE.getType(), Gender.OTHER.getType());
        assertNotEquals(Gender.MALE.getType(), Gender.OTHER.getType());
    }

}