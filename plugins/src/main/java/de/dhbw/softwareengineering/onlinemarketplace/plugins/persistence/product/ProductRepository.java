package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence.product;

import de.dhbw.softwareengineering.onlinemarketplace.domain.product.IProductRepository;
import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public class ProductRepository implements IProductRepository {

    private final DataProductRepository dataProductRepository;

    @Autowired
    public ProductRepository(final DataProductRepository dataUserRepository) {
        this.dataProductRepository = dataUserRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return dataProductRepository.findAll();
    }

    @Override
    public List<Product> findAllProductsFromUser(UUID userId) {
        return dataProductRepository.findAll();
    }

    @Override
    public Product findProductWithId(UUID id) {
        var product = dataProductRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Product with id " + id + " not found");
        }
        return product.get();
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
