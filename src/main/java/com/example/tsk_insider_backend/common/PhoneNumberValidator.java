package com.example.tsk_insider_backend.common;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            PhoneNumberUtil util = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber number = util.parse(value, "PL");
            return util.isValidNumber(number);
        } catch (NumberParseException e) {
            return false;
        }
    }
}
