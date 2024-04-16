package de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart;

import org.apache.commons.lang3.Validate;

import java.util.UUID;

public record CartItem(UUID productId, int quantity) {
    public CartItem {
        Validate.notNull(productId);
        Validate.isTrue(quantity > 0);
    }
}
