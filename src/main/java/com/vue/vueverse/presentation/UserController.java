package com.vue.vueverse.presentation;

import com.vue.vueverse.application.UserManagement;
import com.vue.vueverse.infrastructure.entity.UserEntity;
import com.vue.vueverse.presentation.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserManagement userManagement;


    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userManagement.update(id));
    }

}
