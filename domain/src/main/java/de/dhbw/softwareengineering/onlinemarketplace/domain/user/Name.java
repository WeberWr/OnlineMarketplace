package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import org.apache.commons.lang3.Validate;

public record Name(String firstName, String lastName) {
    public Name {
        Validate.notBlank(firstName);
        Validate.notBlank(lastName);
    }
}