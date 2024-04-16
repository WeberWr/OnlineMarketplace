package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document
public final class Product {
    private final UUID id;
    private final UUID ownerId;
    private final String name;
    private final double price;
    private final int amount;

    public Product(UUID id, UUID ownerId, String name, double price, int amount) {
        //ToDo: validate all and remove Id here
        this.id = id;
        this.ownerId = ownerId;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.ownerId, that.ownerId) &&
                Objects.equals(this.name, that.name) &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price) &&
                this.amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ownerId, name, price, amount);
    }

    @Override
    public String toString() {
        return "Product[" +
                "id=" + id + ", " +
                "ownerId=" + ownerId + ", " +
                "name=" + name + ", " +
                "price=" + price + ", " +
                "amount=" + amount + ']';
    }
}