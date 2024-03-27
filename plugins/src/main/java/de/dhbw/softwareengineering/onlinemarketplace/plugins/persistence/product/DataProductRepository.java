package de.dhbw.softwareengineering.onlinemarketplace.plugins.persistence.product;

import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DataProductRepository extends MongoRepository<Product, UUID> {
}
