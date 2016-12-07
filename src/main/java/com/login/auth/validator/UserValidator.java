package com.login.auth.validator;

import com.login.auth.model.User;
import com.login.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.not.empty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "form.username.size");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "form.username.duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.not.empty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "form.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "form.passwordConfirm");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.not.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.not.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "field.not.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "field.not.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "field.not.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "field.not.empty");
        
    }
}
