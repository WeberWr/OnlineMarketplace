package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface DataShoppingCartRepository extends MongoRepository<ShoppingCart, UUID> {
    @Query("{'userId':?0}")
    Optional<ShoppingCart> getShoppingCartOfUser(UUID userId);
}
