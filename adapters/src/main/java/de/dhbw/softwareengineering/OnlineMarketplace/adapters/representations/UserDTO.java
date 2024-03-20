package de.dhbw.softwareengineering.OnlineMarketplace.adapters.representations;

import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Address;
import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;

import java.time.LocalDate;
import java.util.UUID;

public class UserDTO {
    private final UUID id;

    private final Name name;

    private final LocalDate birthDate;

    private final Address address;

    public UserDTO(final UUID id, final Name name, final LocalDate birthDate, final Address address) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }
}

