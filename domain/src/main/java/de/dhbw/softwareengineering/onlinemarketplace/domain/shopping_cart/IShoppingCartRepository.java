package de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart;

import java.util.Optional;
import java.util.UUID;

public interface IShoppingCartRepository {
    Optional<ShoppingCart> getShoppingCartOfUser(UUID userId);

    ShoppingCart update(ShoppingCart shoppingCart);

    UUID create(UUID userId);

    void delete(UUID id);

    void deleteOfUser(UUID userId);
}
