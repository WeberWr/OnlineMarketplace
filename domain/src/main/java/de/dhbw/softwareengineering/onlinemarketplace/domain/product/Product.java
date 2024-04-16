package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import org.apache.commons.lang3.Validate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document
public final class Product {
    private final UUID id;
    private final UUID ownerId;
    private final String name;
    private final double price;
    private final int amount;


    public Product(UUID userId, String name, double price, int amount) {
        Validate.notNull(userId);
        Validate.notBlank(name);
        Validate.isTrue(price > 0);
        Validate.isTrue(amount >= 0);
        this.id = UUID.randomUUID();
        this.ownerId = userId;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public UUID id() {
        return id;
    }

    public UUID ownerId() {
        return ownerId;
    }

    public String name() {
        return name;
    }

    public double price() {
        return price;
    }

    public int amount() {
        return amount;
    }
}