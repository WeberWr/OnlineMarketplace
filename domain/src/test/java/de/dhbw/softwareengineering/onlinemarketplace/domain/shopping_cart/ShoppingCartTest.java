package de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {
    private ShoppingCart cart;
    private CartItem item1;
    private CartItem item2;
    private CartItem item3;

    @BeforeEach
    public void setUp() {
        UUID userId = UUID.randomUUID();
        cart = new ShoppingCart(userId);

        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        item1 = new CartItem(productId1, 2);
        item2 = new CartItem(productId1, 3);
        item3 = new CartItem(productId2, 1);
    }

    @Test
    public void testAddItem_NewItems() {
        cart.addItem(item1);
        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).quantity());

        cart.addItem(item3);
        assertEquals(2, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).quantity());
        assertEquals(1, cart.getItems().get(1).quantity());
    }

    @Test
    public void testAddItem_AggregateExistingItem() {
        cart.addItem(item1);
        cart.addItem(item2);

        assertEquals(1, cart.getItems().size());
        assertEquals(5, cart.getItems().get(0).quantity());
    }

    @Test
    public void testRemoveItem() {
        cart.addItem(item1);
        cart.addItem(item3);
        cart.removeItemWithProduct(item1.productId());

        assertEquals(1, cart.getItems().size());
        assertEquals(item3.productId(), cart.getItems().get(0).productId());
    }

    @Test
    public void testRemoveItem_NotFound() {
        cart.addItem(item1);
        cart.removeItemWithProduct(UUID.randomUUID());

        assertEquals(1, cart.getItems().size());
    }
}
