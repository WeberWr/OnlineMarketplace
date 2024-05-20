package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.validation.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    private EmailValidator validator;

    @BeforeEach
    public void setUp() {
        validator = EmailValidator.getInstance();
    }

    @Test
    public void testValidEmails() {
        assertTrue(validator.isValidEmail("example@example.com"));
        assertTrue(validator.isValidEmail("user.name@domain.co"));
        assertTrue(validator.isValidEmail("user-name@domain.info"));
        assertTrue(validator.isValidEmail("user.name@domain.name"));
    }

    @Test
    public void testInvalidEmails() {
        assertFalse(validator.isValidEmail("userdomain.com"));
        assertFalse(validator.isValidEmail("user@.com.my"));
        assertFalse(validator.isValidEmail("@you.me.net"));
        assertFalse(validator.isValidEmail("user.name@.com"));
        assertFalse(validator.isValidEmail("user.name@com."));
        assertFalse(validator.isValidEmail("user.name@domain.com.a"));
    }

    @Test
    public void testEmailsWithNull() {
        assertFalse(validator.isValidEmail(null));
    }
}
