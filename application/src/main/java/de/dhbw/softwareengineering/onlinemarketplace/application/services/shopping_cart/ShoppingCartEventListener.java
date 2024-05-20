package de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.application.services.user.UserCreatedEvent;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.user.UserDeletedEvent;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartEventListener {

    private final IShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartEventListener(IShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        var shoppingCart = new ShoppingCart(event.getUserId());
        shoppingCartRepository.create(shoppingCart);
    }

    @EventListener
    public void handleUserDeletedEvent(UserDeletedEvent event) {
        var shoppingCart = shoppingCartRepository.getShoppingCartOfUser(event.getUserId());
        shoppingCart.ifPresent(shoppingCartRepository::delete);
    }
}
