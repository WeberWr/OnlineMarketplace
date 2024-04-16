package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingCartDto {
    private UUID id;
    private UUID userId;
    private List<CartItem> items = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}