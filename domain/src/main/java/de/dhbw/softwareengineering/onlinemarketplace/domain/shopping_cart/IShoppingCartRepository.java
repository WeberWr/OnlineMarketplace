package de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart;

import java.util.Optional;
import java.util.UUID;

public interface IShoppingCartRepository {
    ShoppingCart create(ShoppingCart shoppingCart);

    Optional<ShoppingCart> getShoppingCartOfUser(UUID userId);

    ShoppingCart update(ShoppingCart shoppingCart);

    void deleteById(UUID id);

    void delete(ShoppingCart shoppingCart);
}
