package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;

import java.util.UUID;

public record RemoveItemFromShoppingCartRequest(ShoppingCart shoppingCart, UUID productId) {
}