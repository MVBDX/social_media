package com.vue.vueverse.application;

import com.vue.vueverse.domain.user.UserRepository;
import com.vue.vueverse.infrastructure.entity.UserEntity;
import com.vue.vueverse.presentation.dto.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementImpl implements UserManagement {
    private final UserRepository userRepository;

    public UserManagementImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public List<UserResponse> findAll() {
//       return userRepository.findAll();
//    }

    @Override
    public UserResponse update(Long id) {
        var mapper = new ModelMapper();
        return userRepository.findById(id).map(user -> mapper.map(user, UserResponse.class))
                .orElseThrow(() -> new IllegalArgumentException(" not found"));

    }
}
