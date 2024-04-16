package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public class ShoppingCartRepository implements IShoppingCartRepository {

    private final DataShoppingCartRepository dataShoppingCartRepository;

    @Autowired
    public ShoppingCartRepository(final DataShoppingCartRepository dataShoppingCartRepository) {
        this.dataShoppingCartRepository = dataShoppingCartRepository;
    }
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return dataShoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> getShoppingCartOfUser(UUID userId) {
        return dataShoppingCartRepository.getShoppingCartOfUser(userId);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return dataShoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteById(UUID id) {
        dataShoppingCartRepository.deleteById(id);
    }

    @Override
    public void delete(ShoppingCart shoppingCart) {
        dataShoppingCartRepository.delete(shoppingCart);
    }
}
