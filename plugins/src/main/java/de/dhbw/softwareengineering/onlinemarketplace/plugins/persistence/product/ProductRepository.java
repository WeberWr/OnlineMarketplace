package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence.product;

import de.dhbw.softwareengineering.onlinemarketplace.domain.product.IProductRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class ProductRepository implements IProductRepository {

    private final DataProductRepository dataProductRepository;

    @Autowired
    public ProductRepository(final DataProductRepository dataProductRepository) {
        this.dataProductRepository = dataProductRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return dataProductRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsFromUser(UUID userId) {
        return dataProductRepository.findAll().stream().filter(product -> product.getUserId().equals(userId)).toList();
    }

    @Override
    public Optional<Product> findProductWithId(UUID id) {
        return dataProductRepository.findById(id);
    }

    @Override
    public Product create(Product product) {
        return dataProductRepository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        dataProductRepository.deleteById(id);
    }
}
