package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.ProductDto;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.ProductToProductDtoMapper;
import de.dhbw.softwareengineering.onlinemarketplace.plugins.authentification.ContextProvider;
import de.dhbw.softwareengineering.onlinemarketplace.services.product.CreateProductRequest;
import de.dhbw.softwareengineering.onlinemarketplace.services.product.ProductService;
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
    private final ContextProvider contextProvider;
    private final ProductToProductDtoMapper toDtoMapper = new ProductToProductDtoMapper();

    @Autowired
    public ProductController(ProductService productService, ContextProvider contextProvider) {
        this.productService = productService;
        this.contextProvider = contextProvider;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable UUID id) {
        return productService.getProductWithId(id)
                .map(toDtoMapper)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.getAllProducts().stream()
                .map(toDtoMapper)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductsOfUser() {
        var currentUserId = contextProvider.getUser().id();
        List<ProductDto> products = productService.getAllProductsFromUser(currentUserId).stream()
                .map(toDtoMapper)
                .collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest request) {
        var createdProduct = productService.create(request, contextProvider.getUser().id());
        return ResponseEntity.ok(toDtoMapper.apply(createdProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
