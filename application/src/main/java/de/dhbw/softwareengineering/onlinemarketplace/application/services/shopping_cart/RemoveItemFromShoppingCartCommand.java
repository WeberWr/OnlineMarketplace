package de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart;

import java.util.UUID;

public record RemoveItemFromShoppingCartCommand(UUID userId, UUID productId) {
}
