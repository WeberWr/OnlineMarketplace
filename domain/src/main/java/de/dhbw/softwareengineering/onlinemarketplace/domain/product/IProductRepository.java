package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductRepository {
    List<Product> getAllProducts();
    List<Product> getAllProductsFromUser(UUID userId);
    Optional<Product> getProductWithId(UUID id);
    Product create(Product product);
    void deleteById(UUID id);
}
