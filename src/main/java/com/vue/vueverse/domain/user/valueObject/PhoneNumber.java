package com.vue.vueverse.domain.user.valueObject;

import com.vue.vueverse.domain.user.User;
import com.vue.vueverse.domain.user.UserException;
import com.vue.vueverse.domain.user.UserRepository;

import java.util.Objects;


public class PhoneNumber {
    private final String phoneNumber;
    private final UserRepository userRepository;

    public PhoneNumber(String phoneNumber, UserRepository userRepository) {
        validate();
        this.phoneNumber = phoneNumber;
        this.userRepository = userRepository;
    }

    public boolean updatePhoneNumber(User user, String phoneNumber) {

        User currentUser = userRepository.findByUsernameOrEmail(user.getUsername().getName(), user.getEmail().getEmail())
                .orElseThrow(() -> new UserException("User doesn't exist.")); //TODO: Using bundle message for multilingual and prevent duplication message

        validate();

        if (!Objects.equals(currentUser.getPhonenumber().phoneNumber, phoneNumber))
            return userRepository.save(new User(user.getUsername(), user.getPassword(), user.getEmail(),
                    new PhoneNumber(phoneNumber, userRepository), user.getGender()));

        return false;
    }

    public void validate() throws UserException {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            if (!phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumber, "ZZ"))) {
                throw new UserException("Invalid phone number");
            }
        } catch (NumberParseException exception) {
            throw new UserException(exception.getMessage());
        }
    }

}
