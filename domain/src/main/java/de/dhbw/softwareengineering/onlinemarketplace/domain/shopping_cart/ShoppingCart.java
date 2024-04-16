package de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

@Document
public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();
    private UUID id;
    private UUID userId;

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

    //ToDo increase/decrease quantity

    public void removeItem(Product product) {
        items.removeIf(item -> item.productId().equals(product));
    }
}
