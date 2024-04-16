package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product;

import de.dhbw.softwareengineering.onlinemarketplace.domain.valueObject.Name;

import java.util.UUID;

public class ProductDto {
    private UUID id;
    private String name;
    private double price;
    private int amount;
    private UUID ownerId;

    public ProductDto(UUID id, UUID ownerId, String name, double price, int amount) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
