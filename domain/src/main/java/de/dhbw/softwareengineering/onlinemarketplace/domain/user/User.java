package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Address;
import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;
import java.util.UUID;

public record User(UUID id, Name name, Address address, String email, String password) {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                '}';
    }
}