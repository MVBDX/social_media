package com.vue.vueverse.domain.user;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import java.util.Objects;


public class PhoneNumber {
    private final String phoneNumber;
    private final UserRepository userRepository;

    public PhoneNumber(String phoneNumber, UserRepository userRepository) {
        isValidPhoneNumber(phoneNumber);
        this.phoneNumber = phoneNumber;
        this.userRepository = userRepository;
    }

    private void isValidPhoneNumber(String phoneNumber) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(phoneNumber, "ZZ"));
        } catch (NumberParseException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public boolean updatePhoneNumber(User user, PhoneNumber phoneNumber) {

        User currentUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())
                .orElseThrow(() -> new UserException("user doesn't exist "));

        isValidPhoneNumber(phoneNumber.phoneNumber);

        if (!Objects.equals(currentUser.getPhonenumber().phoneNumber, phoneNumber.phoneNumber))
            return userRepository.save(new User(user.getUsername(), user.getPassword(), user.getEmail(),
                    phoneNumber, user.getGender()));

        return false;
    }

}
