package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Product {

    @Id
    private UUID id;

    private Name name;

    private UUID userId;

    public Product(UUID id, Name name, UUID userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public UUID getUserId() {
        return userId;
    }
}