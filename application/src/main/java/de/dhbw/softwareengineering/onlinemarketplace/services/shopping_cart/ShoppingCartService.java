package de.dhbw.softwareengineering.onlinemarketplace.services.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.OrderService;
import de.dhbw.softwareengineering.onlinemarketplace.domain.ProductDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.domain.ShoppingCartDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartService {
    private final IShoppingCartRepository repository;
    private final OrderService orderService;

    @Autowired
    public ShoppingCartService(IShoppingCartRepository repository, OrderService orderService) {
        this.repository = repository;
        this.orderService = orderService;
    }

    public Optional<ShoppingCart> getShoppingCartOfUser(UUID userId) {
        return repository.getShoppingCartOfUser(userId);
    }

    public double getTotalPrize(UUID userId) throws ShoppingCartDoesNotExistException, ProductDoesNotExistException {
        return orderService.getTotalPrizeOfShoppingCart(userId);
    }

    public ShoppingCart removeItem(RemoveItemFromShoppingCartRequest request) {
        request.shoppingCart().removeItemWithProduct(request.productId());
        return repository.update(request.shoppingCart());
    }

    public ShoppingCart addItem(AddItemToShoppingCartRequest request) throws ProductDoesNotExistException {
        orderService.checkIfAllProductExists(request.cartItem().productId());
        request.shoppingCart().addItem(request.cartItem());
        return repository.update(request.shoppingCart());
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

}
