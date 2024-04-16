package de.dhbw.softwareengineering.onlinemarketplace.services.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.CartItem;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceTest {

    @Mock
    private IShoppingCartRepository repository;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private ShoppingCart cart;
    private UUID userId;
    private UUID productId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        productId = UUID.randomUUID();

        CartItem cartItem = new CartItem(productId, 1);
        cart = new ShoppingCart(userId);
        cart.addItem(cartItem);
    }

    @Test
    void getShoppingCartOfUser() {
        when(repository.getShoppingCartOfUser(userId)).thenReturn(Optional.of(cart));

        Optional<ShoppingCart> result = shoppingCartService.getShoppingCartOfUser(userId);

        assertTrue(result.isPresent());
        assertEquals(cart, result.get());
        verify(repository).getShoppingCartOfUser(userId);
    }

    @Test
    void addItem() {
        CartItem item = new CartItem(productId, 1);
        AddItemToShoppingCartRequest request = new AddItemToShoppingCartRequest(cart, item);
        when(repository.update(cart)).thenReturn(cart);

        ShoppingCart updatedCart = shoppingCartService.addItem(request);

        assertEquals(cart, updatedCart);
        verify(repository).update(cart);
    }


    @Test
    void removeItem() {
        RemoveItemFromShoppingCartRequest request = new RemoveItemFromShoppingCartRequest(cart, productId);
        when(repository.update(cart)).thenReturn(cart);

        ShoppingCart updatedCart = shoppingCartService.removeItem(request);

        assertEquals(cart, updatedCart);
        verify(repository).update(cart);
    }

    @Test
    void deleteById() {
        shoppingCartService.deleteById(userId);

        verify(repository).deleteById(userId);
    }
}
