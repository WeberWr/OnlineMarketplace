package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.AddItemToShoppingCartRequest;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.RemoveItemFromShoppingCartRequest;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.ShoppingCartDto;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.ShoppingCartToShoppingCartDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ProductDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.domain.shopping_cart_management.ShoppingCartDoesNotExistException;
import de.dhbw.softwareengineering.onlinemarketplace.plugins.authentification.ContextProvider;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart.AddItemToShoppingCartCommand;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart.RemoveItemFromShoppingCartCommand;
import de.dhbw.softwareengineering.onlinemarketplace.application.services.shopping_cart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<Double> getTotalPrize() throws ShoppingCartDoesNotExistException, ProductDoesNotExistException {
        var prize = shoppingCartService.getTotalPrize(contextProvider.getUser().id());
        return ResponseEntity.ok(prize);
    }

    @PutMapping("/addItem")
    public ResponseEntity<ShoppingCartDto> addItem(@RequestBody AddItemToShoppingCartRequest request) throws ProductDoesNotExistException {
        var createCommand = new AddItemToShoppingCartCommand(request.shoppingCart(), request.cartItem());
        var updatedShoppingCart = shoppingCartService.addItem(createCommand);
        return ResponseEntity.ok(toDtoMapper.apply(updatedShoppingCart));
    }

    @PutMapping("/removeItem")
    public ResponseEntity<ShoppingCartDto> removeItem(@RequestBody RemoveItemFromShoppingCartRequest request) {
        var removeCommand = new RemoveItemFromShoppingCartCommand(request.shoppingCart(), request.productId());
        var updatedShoppingCart = shoppingCartService.removeItem(removeCommand);
        return ResponseEntity.ok(toDtoMapper.apply(updatedShoppingCart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        shoppingCartService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

