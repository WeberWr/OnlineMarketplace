package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.mappers;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.ProductDTO;
import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductToProductDTO implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product user) {

        return map(user);
    }

    private ProductDTO map(final Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getUserId());
    }
}
