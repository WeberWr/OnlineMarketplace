package de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.mappers;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.ProductDTO;
import de.dhbw.softwareengineering.onlinemarketplace.domain.product.Product;
import de.dhbw.softwareengineering.onlinemarketplace.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDTOToProduct implements Function<ProductDTO, Product> {

    @Override
    public Product apply(ProductDTO productDTO) {

        return map(productDTO);
    }

    private User map(final ProductDTO productDTO) {
        return new User(productDTO.getId(), productDTO.getName(), productDTO.getUserId());
    }
}
