package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product;

import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;

import java.util.UUID;

public class ProductDTO {

    private UUID id;

    private Name name;

    private UUID userId;

    public ProductDTO(UUID id, Name name, UUID userId) {
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

