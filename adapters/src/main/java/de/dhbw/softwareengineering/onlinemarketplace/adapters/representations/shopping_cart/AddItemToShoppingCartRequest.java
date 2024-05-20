package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.CartItem;

public record AddItemToShoppingCartRequest(CartItem cartItem) {
}
