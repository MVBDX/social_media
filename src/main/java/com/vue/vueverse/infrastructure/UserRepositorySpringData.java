package com.vue.vueverse.infrastructure;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserRepository;
import com.vue.vueverse.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositorySpringData implements UserRepository {
    private final UserJpaRepository repository;

    public UserRepositorySpringData(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        //todo map to user
        repository.findByEmailOrUsername(email, username).orElseThrow(
                () -> new RuntimeException("not found")
        );
        return null;
    }

    @Override
    public boolean save(User user) {
        //todo map user to user entity and then save it
        return false;
    }


}
