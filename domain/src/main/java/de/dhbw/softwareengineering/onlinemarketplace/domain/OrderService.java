package de.dhbw.softwareengineering.onlinemarketplace.domain;

import de.dhbw.softwareengineering.onlinemarketplace.domain.product.IProductRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    private final IProductRepository productRepository;
    private final IShoppingCartRepository shoppingCartRepository;

    @Autowired
    public OrderService(IProductRepository productRepository, IShoppingCartRepository shoppingCartRepository) {
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public double getTotalPrizeOfShoppingCart(UUID userId) throws ShoppingCartDoesNotExistException, ProductDoesNotExistException {
        var shoppingCart = shoppingCartRepository.getShoppingCartOfUser(userId);
        if (shoppingCart.isEmpty()){
            throw new ShoppingCartDoesNotExistException();
        }

        double prize = 0;
        for (var cartItem : shoppingCart.get().getItems()){
            var product = productRepository.getProductWithId(cartItem.productId());
            if (product.isEmpty()){
                throw new ProductDoesNotExistException();
            }
            prize += product.get().getPrice() * cartItem.quantity();
        }
        return prize;
    }

    public void checkIfAllProductExists(UUID productId) throws ProductDoesNotExistException {
        var product = productRepository.getProductWithId(productId);
        if (product.isEmpty()){
            throw new ProductDoesNotExistException();
        }
    }
}
