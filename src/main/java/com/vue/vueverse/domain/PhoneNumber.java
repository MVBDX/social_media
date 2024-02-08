package com.vue.vueverse.domain;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;



public record PhoneNumber(String phoneNumber) {
    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        isValidPhoneNumber();
    }

    private void isValidPhoneNumber() {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            phoneNumberUtil.isValidNumber(phoneNumberUtil.parse(this.phoneNumber, "ZZ"));
        } catch (NumberParseException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public boolean updatePhoneNumber() {
        return false;
    }
}
