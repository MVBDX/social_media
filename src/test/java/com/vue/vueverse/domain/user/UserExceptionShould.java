package com.vue.vueverse.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserExceptionShould {
    @Test
    void createExceptionWithMessage() {
        String errorMessage = "This is an error message.";
        UserException userException = new UserException(errorMessage);

        assertEquals(errorMessage, userException.getMessage());
    }

    @Test
    void createExceptionWithNullMessage() {
        UserException userException = new UserException(null);

        assertNull(userException.getMessage());
    }
}