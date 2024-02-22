package com.vue.vueverse.domain.user.valueObject;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class PasswordShould {

    @Test
    void allowValidPassword() {

        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);

        assertDoesNotThrow(new Password("Abcd1234", mockUserRepository)::validate);
        assertDoesNotThrow(new Password("ali33@A234", mockUserRepository)::validate);
        assertDoesNotThrow(new Password("Ba234@#$Rcjk", mockUserRepository)::validate);
    }

    @Test
    void throwExceptionForInvalidPassword() {
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);

        assertThrows(UserException.class, () -> new Password("invalid", mockUserRepository));
        assertThrows(UserException.class, () -> new Password("23423", mockUserRepository));
        assertThrows(UserException.class, () -> new Password("sdf342", mockUserRepository));
    }

    @Test
    void allowPasswordUpdateWhenDifferent() {
        // Arrange
        String oldPassword = "OldPassword123";
        String newPassword = "NewPassword456";
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);

        User mockUser = Mockito.mock(User.class);
        Password mockPassword = Mockito.mock(Password.class);
        Username mockUsername = Mockito.mock(Username.class);
        Email mockEmail = Mockito.mock(Email.class);

        Mockito.when(mockUser.getUsername()).thenReturn(mockUsername);
        Mockito.when(mockUsername.getName()).thenReturn("example_username");

        Mockito.when(mockUser.getEmail()).thenReturn(mockEmail);
        Mockito.when(mockEmail.getEmail()).thenReturn("example@example.com");

        Mockito.when(mockUser.getPassword()).thenReturn(mockPassword);
        Mockito.when(mockPassword.getPassword()).thenReturn(oldPassword);
        Mockito.when(mockUserRepository.findByUsernameOrEmail(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(java.util.Optional.of(mockUser));
        Mockito.when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(true);

        Password password = new Password(oldPassword, mockUserRepository);

        boolean result = password.updatePassword(mockUser, newPassword);

        assertTrue(result);
        Mockito.verify(mockUserRepository).save(Mockito.any(User.class));
    }


    @Test
    void denyPasswordUpdateWhenSame() {

        String oldPassword = "SamePassword123";

        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        User mockUser = Mockito.mock(User.class);
        Password mockPassword = Mockito.mock(Password.class);
        Username mockUsername = Mockito.mock(Username.class);
        Email mockEmail = Mockito.mock(Email.class);

        Mockito.when(mockUser.getUsername()).thenReturn(mockUsername);
        Mockito.when(mockUsername.getName()).thenReturn("example_username");
        Mockito.when(mockUser.getEmail()).thenReturn(mockEmail);
        Mockito.when(mockEmail.getEmail()).thenReturn("example@example.com");
        Mockito.when(mockUser.getPassword()).thenReturn(mockPassword);
        Mockito.when(mockPassword.getPassword()).thenReturn(oldPassword);
        Mockito.when(mockUser.getPasswordValue()).thenReturn(oldPassword);


        Mockito.when(mockUserRepository.findByUsernameOrEmail(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(mockUser));

        Password password = new Password(oldPassword, mockUserRepository);



        assertThrows(UserException.class, () -> password.updatePassword(mockUser, oldPassword));
    }

}







