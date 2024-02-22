package com.vue.vueverse.application;

import com.vue.vueverse.domain.user.UserRepository;
import com.vue.vueverse.infrastructure.entity.UserEntity;
import com.vue.vueverse.presentation.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManagementImpl implements UserManagement {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

//    @Override
//    public List<UserResponse> findAll() {
//       return userRepository.findAll();
//    }

    @Override
    public UserResponse update(Long id) {
        return userRepository.findById(id).map(user -> modelMapper.map(user, UserResponse.class))
                .orElseThrow(() -> new IllegalArgumentException(" not found"));

    }
}
