package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.mappers;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.ProductDTO;
import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDTOToProduct implements Function<ProductDTO, Product> {

    @Override
    public Product apply(ProductDTO productDTO) {

        return map(productDTO);
    }

    private Product map(final ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getUserId());
    }
}
