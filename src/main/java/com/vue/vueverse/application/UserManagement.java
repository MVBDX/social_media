package com.vue.vueverse.application;

import com.vue.vueverse.presentation.dto.UserResponse;

public interface UserManagement {

//    List<UserEntity> findAll();


    UserResponse update(Long id);
}
