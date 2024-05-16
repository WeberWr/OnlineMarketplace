package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.CartItem;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;

public record AddItemToShoppingCartRequest(ShoppingCart shoppingCart, CartItem cartItem) {
}
