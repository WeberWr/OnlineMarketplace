package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import org.apache.commons.lang3.Validate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public final class Product {
    @Id
    private final UUID id;
    private UUID userId;
    private String name;
    private String description;
    private double price;

    public Product(UUID userId, String name, String description, double price) throws IllegalArgumentException{
        try {
            Validate.notNull(userId);
            Validate.notBlank(name);
            Validate.notBlank(description);
            Validate.isTrue(price > 0);
        } catch (IllegalArgumentException e){
            throw e;
        }

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description; }

    public double getPrice() {
        return price;
    }
}