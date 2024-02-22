package com.vue.vueverse.domain.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsernameOrEmail(String username, String email);

    boolean save(User user);


//
//    List<User> findAll();

    Optional<User> findById(Long id);


}
