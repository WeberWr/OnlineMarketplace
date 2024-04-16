package de.dhbw.softwareengineering.onlinemarketplace.services.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;

public record RemoveItemFromShoppingCart(ShoppingCart shoppingCart, Product product) {
}
