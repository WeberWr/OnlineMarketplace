package de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

public record Name(String firstName, String lastName) {
    public Name {
        Validate.notBlank(firstName);
        Validate.notBlank(lastName);
    }
}