package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.AddItemToShoppingCartRequest;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.RemoveItemFromShoppingCartRequest;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.ShoppingCartDto;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.ShoppingCartToShoppingCartDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart.AddItemToShoppingCartCommand;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart.RemoveItemFromShoppingCartCommand;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart.ShoppingCartService;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart.ShoppingCart;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ProductDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ShoppingCartDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.plugins.authentification.ContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ContextProvider contextProvider;
    private final ShoppingCartToShoppingCartDtoMapper toDtoMapper = new ShoppingCartToShoppingCartDtoMapper();

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ContextProvider contextProvider) {
        this.shoppingCartService = shoppingCartService;
        this.contextProvider = contextProvider;
    }

    @GetMapping()
    public ResponseEntity<ShoppingCartDto> getShoppingCartOfCurrentUser() {
        return shoppingCartService.getShoppingCartOfUser(contextProvider.getUser().id())
                .map(toDtoMapper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/totalPrize")
    public ResponseEntity<Double> getTotalPrize() {
        double prize;
        try {
            prize = shoppingCartService.getTotalPrize(contextProvider.getUser().id());
        } catch (ShoppingCartDoesNotExistException | ProductDoesNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(prize);
    }

    @PutMapping("/addItem")
    public ResponseEntity<ShoppingCartDto> addItem(@RequestBody AddItemToShoppingCartRequest request) {
        var currentUserId = contextProvider.getUser().id();
        var createCommand = new AddItemToShoppingCartCommand(currentUserId, request.cartItem());
        ShoppingCart updatedShoppingCart;
        try {
            updatedShoppingCart = shoppingCartService.addItem(createCommand);

        } catch (ProductDoesNotExistException | ShoppingCartDoesNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(toDtoMapper.apply(updatedShoppingCart));
    }

    @PutMapping("/removeItem")
    public ResponseEntity<ShoppingCartDto> removeItem(@RequestBody RemoveItemFromShoppingCartRequest request) {
        var currentUserId = contextProvider.getUser().id();
        var removeCommand = new RemoveItemFromShoppingCartCommand(currentUserId, request.productId());
        ShoppingCart updatedShoppingCart;
        try {
            updatedShoppingCart = shoppingCartService.removeItem(removeCommand);
        } catch (ShoppingCartDoesNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(toDtoMapper.apply(updatedShoppingCart));
    }
}

