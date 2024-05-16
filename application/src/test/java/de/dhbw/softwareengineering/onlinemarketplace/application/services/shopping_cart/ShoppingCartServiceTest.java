package de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.CartItem;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.IShoppingCartRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ProductDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ShoppingCartManagementService;
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
    private IShoppingCartRepository shoppingCartRepository;

    @Mock
    private ShoppingCartManagementService shoppingCartManagementService;

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
        when(shoppingCartRepository.getShoppingCartOfUser(userId)).thenReturn(Optional.of(cart));

        Optional<ShoppingCart> result = shoppingCartService.getShoppingCartOfUser(userId);

        assertTrue(result.isPresent());
        assertEquals(cart, result.get());
        verify(shoppingCartRepository).getShoppingCartOfUser(userId);
    }

    @Test
    void addItem() throws ProductDoesNotExistException {
        CartItem item = new CartItem(productId, 1);
        AddItemToShoppingCartCommand request = new AddItemToShoppingCartCommand(cart, item);
        when(shoppingCartRepository.update(cart)).thenReturn(cart);
        doNothing().when(shoppingCartManagementService).checkIfProductOfCartItemExists(item);

        ShoppingCart updatedCart = shoppingCartService.addItem(request);

        assertEquals(cart, updatedCart);
        verify(shoppingCartRepository).update(cart);
    }


    @Test
    void removeItem() {
        RemoveItemFromShoppingCartCommand request = new RemoveItemFromShoppingCartCommand(cart, productId);
        when(shoppingCartRepository.update(cart)).thenReturn(cart);

        ShoppingCart updatedCart = shoppingCartService.removeItem(request);

        assertEquals(cart, updatedCart);
        verify(shoppingCartRepository).update(cart);
    }

    @Test
    void deleteById() {
        shoppingCartService.deleteById(userId);

        verify(shoppingCartRepository).deleteById(userId);
    }
}
