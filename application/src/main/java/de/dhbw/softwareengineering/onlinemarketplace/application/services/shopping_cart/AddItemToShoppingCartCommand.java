package de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.CartItem;

import java.util.UUID;

public record AddItemToShoppingCartCommand(UUID userId, CartItem cartItem) {
}
