package com.vue.vueverse.domain.user.valueObject.validate;

import com.vue.vueverse.domain.user.UserException;

public interface Validator {
    void validate() throws UserException;
}
