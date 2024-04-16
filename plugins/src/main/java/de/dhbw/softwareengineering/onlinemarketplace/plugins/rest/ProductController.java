package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.ProductDto;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.mappers.ProductDtoToProductMapper;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.mappers.ProductToProductDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.user.UserDto;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import de.dhbw.softwareengineering.onlinemarketplace.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductToProductDtoMapper toDtoMapper = new ProductToProductDtoMapper();
    private final ProductDtoToProductMapper toProductMapper = new ProductDtoToProductMapper();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        return productService.getProductWithId(id)
                .map(toDtoMapper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts().stream()
                .map(toDtoMapper)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = toUserMapper.apply(userDto);
        User createdUser = productService.createOrUpdateUser(user);
        return ResponseEntity.ok(toDtoMapper.apply(createdUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        User user = toUserMapper.apply(userDto);
        User updatedUser = productService.createOrUpdateUser(user);
        return ResponseEntity.ok(toDtoMapper.apply(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        productService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
