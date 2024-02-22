package com.vue.vueverse.domain.user.valueObject;

import com.vue.vueverse.domain.user.Gender;
import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UsernameShould {
    @Test
    void does_NotThrow_Exception_WithValidUsername() {

        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);


        assertDoesNotThrow(() -> new Username("valid_username", mockUserRepository));
        assertDoesNotThrow(() -> new Username("ali33", mockUserRepository));
        assertDoesNotThrow(() -> new Username("user_name", mockUserRepository));
    }

    @Test
    void throw_Exception_WithInvalidUsername() {
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);

        assertThrows(UserException.class, () -> new Username("invalid_username$", mockUserRepository));
        assertThrows(UserException.class, () -> new Username("i", mockUserRepository));
        assertThrows(UserException.class, () -> new Username("234", mockUserRepository));
    }


    @Test
    void updateUsernameSuccess() {
        String existingUsername = "existing_username";
        String newUsername = "new_username";

        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);

        User mockExistingUser = Mockito.mock(User.class);

        Password mockPassword = Mockito.mock(Password.class);
        Mockito.when(mockPassword.getPassword()).thenReturn("adsfljalj");
        Mockito.when(mockExistingUser.getPassword()).thenReturn(mockPassword);

        Email mockEmail = Mockito.mock(Email.class);
        Mockito.when(mockEmail.getEmail()).thenReturn("lasdfj@gmail.com");
        Mockito.when(mockExistingUser.getEmail()).thenReturn(mockEmail);

        PhoneNumber mockPhoneNumber = Mockito.mock(PhoneNumber.class);
        Mockito.when(mockPhoneNumber.getPhoneNumber()).thenReturn("23425254");
        Mockito.when(mockExistingUser.getPhonenumber()).thenReturn(mockPhoneNumber);

        Username mockUsername = Mockito.mock(Username.class);
        Mockito.when(mockExistingUser.getUsername()).thenReturn(mockUsername);

        Mockito.when(mockUsername.getName()).thenReturn(existingUsername);

        Mockito.when(mockUserRepository.findByUsernameOrEmail(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(mockExistingUser));


        Mockito.when(mockUserRepository.save(Mockito.any(User.class))).thenReturn(true);


        Username username = new Username(existingUsername, mockUserRepository);

        boolean result = username.updateUsername(mockExistingUser, newUsername);

        assertTrue(result);
        Mockito.verify(mockUserRepository).save(Mockito.any(User.class));
    }

    @Test
    void updateUsernameNoChange() {

        String existingUsername = "existing_username";

        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);

        User mockExistingUser = Mockito.mock(User.class);
        Username mockUsername = Mockito.mock(Username.class);
        Email mockEmail = Mockito.mock(Email.class);

        Mockito.when(mockExistingUser.getUsername()).thenReturn(mockUsername);
        Mockito.when(mockUsername.getName()).thenReturn(existingUsername);
        Mockito.when(mockExistingUser.getEmail()).thenReturn(mockEmail);
        Mockito.when(mockEmail.getEmail()).thenReturn("example@example.com");
        Mockito.when(mockExistingUser.getUsernameValue()).thenReturn("existing_username");
        Mockito.when(mockUserRepository.findByUsernameOrEmail(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(mockExistingUser));

        Username username = new Username(existingUsername, mockUserRepository);
        assertThrows(UserException.class, () -> username.updateUsername(mockExistingUser,"existing_username"));
    }
}


