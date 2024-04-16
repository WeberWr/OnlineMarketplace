package de.dhbw.softwareengineering.onlinemarketplace.services.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartService {
    private final IShoppingCartRepository repository;

    @Autowired
    public ShoppingCartService(IShoppingCartRepository repository) {
        this.repository = repository;
    }

    public Optional<ShoppingCart> getShoppingCartOfUser(UUID userId) {
        return repository.getShoppingCartOfUser(userId);
    }

    public ShoppingCart removeItem(RemoveItemFromShoppingCart request) {
        request.shoppingCart().removeItemWithProduct(request.productId());
        return repository.update(request.shoppingCart());
    }

    public ShoppingCart addItem(AddItemToShoppingCartRequest request) {
        request.shoppingCart().addItem(request.cartItem());
        return repository.update(request.shoppingCart());
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
