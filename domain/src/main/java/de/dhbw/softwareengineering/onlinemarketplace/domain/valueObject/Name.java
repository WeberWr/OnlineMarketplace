package de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject;

public record Name(String firstName, String lastName) {
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}