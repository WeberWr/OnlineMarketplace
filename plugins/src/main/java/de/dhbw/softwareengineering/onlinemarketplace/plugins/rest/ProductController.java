package de.dhbw.softwareengineering.onlinemarketplace.plugins.rest;

import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.ProductDTO;
import de.dhbw.softwareengineering.onlinemarketplace.adapters.representations.product.mappers.ProductToProductDTO;
import de.dhbw.softwareengineering.onlinemarketplace.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/user")
public class ProductController {

    private final ProductService productService;
    private final ProductToProductDTO productToProductDTO;

    @Autowired
    public ProductController(final ProductService productService, final ProductToProductDTO productToProductDTO) {
        this.productService = productService;
        this.productToProductDTO = productToProductDTO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductDTO> getBooks() {
        return this.productService.findAllProducts().stream()
                .map(productToProductDTO)
                .collect(Collectors.toList());
    }
}
