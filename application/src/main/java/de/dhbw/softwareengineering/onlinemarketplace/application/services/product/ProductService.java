package de.dhbw.softwareengineering.onlinemarketplace.application.services.product;

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
        return repository.getAllProducts();
    }

    public List<Product> getAllProductsFromUser(UUID userId) {
        return repository.getAllProductsFromUser(userId);
    }

    public Optional<Product> getProductWithId(UUID id) {
        return repository.getProductWithId(id);
    }

    public Product create(CreateProductCommand request, UUID userId) throws IllegalArgumentException {
        Product product;
        try {
            product = new Product(userId, request.name(), request.description(), request.price());
        } catch (IllegalArgumentException e){
            throw e;
        }
        return repository.create(product);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
