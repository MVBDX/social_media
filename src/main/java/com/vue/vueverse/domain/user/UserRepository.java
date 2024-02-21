package com.vue.vueverse.domain.user;

import com.vue.vueverse.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean save(User user);

}
