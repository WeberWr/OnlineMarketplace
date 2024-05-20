package de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ProductDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ShoppingCartDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ShoppingCartManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartService {
    private final IShoppingCartRepository repository;
    private final ShoppingCartManagementService shoppingCartManagementService;

    @Autowired
    public ShoppingCartService(IShoppingCartRepository repository, ShoppingCartManagementService shoppingCartManagementService) {
        this.repository = repository;
        this.shoppingCartManagementService = shoppingCartManagementService;
    }

    public Optional<ShoppingCart> getShoppingCartOfUser(UUID userId) {
        return repository.getShoppingCartOfUser(userId);
    }

    public double getTotalPrize(UUID userId) throws ShoppingCartDoesNotExistException, ProductDoesNotExistException {
        return shoppingCartManagementService.getTotalPrizeOfShoppingCart(userId);
    }

    public ShoppingCart removeItem(RemoveItemFromShoppingCartCommand request) throws ShoppingCartDoesNotExistException {
        var shoppingCart = repository.getShoppingCartOfUser(request.userId());
        if (shoppingCart.isEmpty()) {
            throw new ShoppingCartDoesNotExistException();
        }
        shoppingCart.get().removeItemWithProduct(request.productId());
        return repository.update(shoppingCart.get());
    }

    public ShoppingCart addItem(AddItemToShoppingCartCommand request) throws ProductDoesNotExistException, ShoppingCartDoesNotExistException {
        shoppingCartManagementService.checkIfProductOfCartItemExists(request.cartItem());
        var shoppingCart = repository.getShoppingCartOfUser(request.userId());
        if (shoppingCart.isEmpty()) {
            throw new ShoppingCartDoesNotExistException();
        }
        shoppingCart.get().addItem(request.cartItem());
        return repository.update(shoppingCart.get());
    }
}
