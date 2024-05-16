package de.dhbw.softwareengineering.onlinemarketplace.domain.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final EmailValidator instance = new EmailValidator();
    private final Pattern pattern;

    private EmailValidator() {
        this.pattern = Pattern.compile(EMAIL_REGEX);
    }

    public static EmailValidator getInstance() {
        return instance;
    }

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
