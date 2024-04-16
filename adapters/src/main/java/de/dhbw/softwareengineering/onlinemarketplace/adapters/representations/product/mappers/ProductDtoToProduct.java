package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.mappers;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.ProductDto;
import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDtoToProduct implements Function<ProductDto, Product> {

    @Override
    public Product apply(ProductDto productDTO) {

        return map(productDTO);
    }

    private Product map(final ProductDto productDTO) {
        return new Product(productDTO.getId(), productDTO.getOwnerId(), productDTO.getName(), productDTO.getPrice(), productDTO.getAmount());
    }
}
