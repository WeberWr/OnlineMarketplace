package de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

@Document
public class ShoppingCart {
    private final UUID id;
    private final UUID userId;
    private final List<CartItem> items;

    public ShoppingCart(UUID userId) {
        id = UUID.randomUUID();
        this.userId = userId;
        items = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem newItem) {
        ListIterator<CartItem> iterator = items.listIterator();
        while (iterator.hasNext()) {
            CartItem currentItem = iterator.next();
            if (currentItem.productId().equals(newItem.productId())) {
                CartItem updatedItem = new CartItem(currentItem.productId(), currentItem.quantity() + newItem.quantity());
                iterator.set(updatedItem);
                return;
            }
        }
        items.add(newItem);
    }

    public void removeItemWithProduct(UUID productId) {
        items.removeIf(item -> item.productId().equals(productId));
    }
}
