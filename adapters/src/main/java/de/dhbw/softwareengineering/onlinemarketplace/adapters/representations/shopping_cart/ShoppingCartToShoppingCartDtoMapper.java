package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShoppingCartToShoppingCartDtoMapper implements Function<ShoppingCart, ShoppingCartDto> {
    @Override
    public ShoppingCartDto apply(ShoppingCart shoppingCart) {
        return map(shoppingCart);
    }

    private ShoppingCartDto map(ShoppingCart shoppingCart) {
        return new ShoppingCartDto(shoppingCart.getId(), shoppingCart.getUserId(), shoppingCart.getItems());
    }
}
