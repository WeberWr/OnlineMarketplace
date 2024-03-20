package de.dhbw.softwareengineering.onlinemarketplace.domain.user;

import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Address;
import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
import java.time.LocalDate;

@Document
public class User {

    @Id
    private UUID id;

    private Name name;

    private LocalDate birthDate;

    private Address address;

    public User(UUID id, Name name, LocalDate birthDate, Address address) {
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