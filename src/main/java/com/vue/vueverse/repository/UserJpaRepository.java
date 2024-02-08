package com.vue.vueverse.repository;



import com.vue.vueverse.domain.user.User;

import java.util.Optional;

public interface UserJpaRepository {
    Optional<User> findUserByUsername(String username);

    boolean updateUser(String username);
}
