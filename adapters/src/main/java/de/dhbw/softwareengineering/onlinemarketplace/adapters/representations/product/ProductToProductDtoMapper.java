package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product;

import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductToProductDtoMapper implements Function<Product, ProductDto> {

    @Override
    public ProductDto apply(Product dto) {
        return map(dto);
    }

    private ProductDto map(Product product) {
        return new ProductDto(product.id(), product.ownerId(), product.name(), product.price(), product.amount());
    }
}
