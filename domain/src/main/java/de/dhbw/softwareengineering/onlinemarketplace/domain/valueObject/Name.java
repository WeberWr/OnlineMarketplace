package de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

public final class Name {
    private final String firstName;
    private final String lastName;

    public Name(String firstName, String lastName) {
        Validate.notBlank(firstName);
        Validate.notBlank(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Name) obj;
        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

}