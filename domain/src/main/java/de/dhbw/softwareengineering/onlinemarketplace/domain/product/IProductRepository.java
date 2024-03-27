package de.dhbw.softwareengineering.onlinemarketplace.domain.product;

import java.util.List;
import java.util.UUID;

public interface IProductRepository {
    List<Product> findAllProducts();

    List<Product> findAllProductsFromUser(UUID userId);

    Product findProductWithId(UUID id);

    Product create(Product product);

    void deleteById(UUID id);
}
