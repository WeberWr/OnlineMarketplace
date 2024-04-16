package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import org.apache.commons.lang3.Validate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public final class Product {
    private final UUID id;
    private final UUID userId;
    private final String name;
    private final double price;

    public Product(UUID userId, String name, double price, int amount) {
        Validate.notNull(userId);
        Validate.notBlank(name);
        Validate.isTrue(price > 0);
        Validate.isTrue(amount >= 0);

        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
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

    public double getPrice() {
        return price;
    }
}