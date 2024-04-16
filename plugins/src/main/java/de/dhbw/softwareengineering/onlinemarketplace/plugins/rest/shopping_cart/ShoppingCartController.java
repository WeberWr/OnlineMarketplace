package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest.shopping_cart;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.ShoppingCartDto;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.shopping_cart.ShoppingCartToShoppingCartDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.plugins.authentification.ContextProvider;
import de.dhbw.softwareengineering.onlinemarketplace.services.shopping_cart.AddItemToShoppingCartRequest;
import de.dhbw.softwareengineering.onlinemarketplace.services.shopping_cart.RemoveItemFromShoppingCartRequest;
import de.dhbw.softwareengineering.onlinemarketplace.services.shopping_cart.ShoppingCartService;
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

    @PostMapping
    public ResponseEntity<ShoppingCartDto> addItem(@RequestBody AddItemToShoppingCartRequest request) {
        var updatedShoppingCart = shoppingCartService.addItem(request);
        return ResponseEntity.ok(toDtoMapper.apply(updatedShoppingCart));
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDto> removeItem(@RequestBody RemoveItemFromShoppingCartRequest request) {
        var updatedShoppingCart = shoppingCartService.removeItem(request);
        return ResponseEntity.ok(toDtoMapper.apply(updatedShoppingCart));
    }

    //ToDo add updateMethod (increase/decrease)
    //ToDo add getTotalPrize
    //ToDo add endpoint to bestellung abschließen

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        shoppingCartService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

