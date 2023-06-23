package com.kos.validation;

import com.kos.annotation.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * pattern is an instance of java.util.regex.Pattern class, used to compile the regular expression pattern.
 * matcher is an instance of java.util.regex.Matcher class, used to match the input email against the pattern.
 * EMAIL_PATTERN is a regular expression pattern for validating email addresses.
 *
 * initialize: This method is from the ConstraintValidator interface.
 * It is called during initialization and can be used to perform any setup tasks. In this case, it is empty since no additional setup is needed.
 *
 * validateEmail:performs the actual pattern matching against the email.
 * It compiles the EMAIL_PATTERN into a Pattern object.
 * It creates a Matcher object using the compiled pattern and the input email.
 * It calls the matches() method on the Matcher object to perform the matching and returns the result.
 *
 * isValid: This method is also from the ConstraintValidator interface. It performs the actual validation of the email.
 * The isValid method receives the email value to be validated as a String and a ConstraintValidatorContext object.
 * It calls the validateEmail method to perform the pattern matching and returns the result.
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return (validateEmail(email));
    }

    private boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_PATTERN); // compiles the regular expression pattern
        matcher = pattern.matcher(email);// applies the compiled pattern to the input email
        return matcher.matches();// checks if the email matches the pattern
    }
}