package com.vue.vueverse.domain.user.valueObject.update;

import com.vue.vueverse.domain.user.User;

public interface Updater<T> {
    boolean update(User user, T t);
}
