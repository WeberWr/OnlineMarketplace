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
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(shoppingCart.getId());
        dto.setUserId(shoppingCart.getUserId());
        dto.setItems(shoppingCart.getItems());
        return dto;
    }
}
