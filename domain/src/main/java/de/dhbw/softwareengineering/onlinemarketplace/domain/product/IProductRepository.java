package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductRepository {
    List<Product> findAllProducts();
    List<Product> findAllProductsFromUser(UUID userId);
    Optional<Product> findProductWithId(UUID id);
    Product createOrUpdate(Product product);
    void deleteById(UUID id);
}
