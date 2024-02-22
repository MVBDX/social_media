package com.vue.vueverse.infrastructure;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserRepository;
import com.vue.vueverse.infrastructure.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
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
        return Optional.empty();
    }

    @Override
    public boolean save(User user) {
        return repository.save(user);
    }


    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }


}
