package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart;

import java.util.UUID;

public record RemoveItemFromShoppingCartRequest(UUID productId) {
}
