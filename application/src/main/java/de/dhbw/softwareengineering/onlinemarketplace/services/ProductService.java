package de.dhbw.softwareengineering.onlinemarketplace.services;

import de.dhbw.softwareengineering.onlinemarketplace.domain.product.IProductRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final IProductRepository repository;

    @Autowired
    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAllProducts();
    }

    public List<Product> getAllProductsFromUser(UUID userId) {
        return repository.findAllProductsFromUser(userId);
    }

    public Optional<Product> getProductWithId(UUID id) {
        return repository.findProductWithId(id);
    }

    public Product createOrUpdate(Product product) {
        return repository.createOrUpdate(product);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
