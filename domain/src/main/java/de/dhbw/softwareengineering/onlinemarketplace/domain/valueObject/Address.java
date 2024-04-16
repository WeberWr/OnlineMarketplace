package de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject;

public record Address(String zipcode, String street, int streetNumber) {

    @Override
    public String toString() {
        return street + " " + streetNumber + ", " + zipcode;
    }
}
