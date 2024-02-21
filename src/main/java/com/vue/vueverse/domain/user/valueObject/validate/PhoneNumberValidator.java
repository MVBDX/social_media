package com.vue.vueverse.domain.user.valueObject.validate;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.vue.vueverse.domain.user.UserException;

public class PhoneNumberValidator implements Validator {
    @Override
    public void validate(String phoneNumber) throws UserException {
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