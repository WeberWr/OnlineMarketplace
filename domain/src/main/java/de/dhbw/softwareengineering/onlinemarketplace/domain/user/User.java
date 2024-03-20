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
}