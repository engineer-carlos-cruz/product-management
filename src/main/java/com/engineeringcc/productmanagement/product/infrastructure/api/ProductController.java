package com.engineeringcc.productmanagement.product.infrastructure.api;

import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository repository) {
        this.productRepository = repository;
    }

    public record ProductRequest(
            Long id,
            String name,
            String description,
            Double price,
            String image
    ) {}

    public record ProductResponse(
            long id,
            String name,
            String description,
            double price
    ) {}

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponseList = products.stream()
                .map(product -> {
                    return new ProductResponse(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice()
                    );
                })
                .toList();

        return ResponseEntity.ok(productResponseList);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductRequest request) {
        Product toSave = Product.builder()
                .id(request.id)
                .name(request.name)
                .description(request.description)
                .price(request.price)
                .image(request.image)
                .build();

        productRepository.save(toSave);
        return ResponseEntity.created(URI.create("/products/" + toSave.getId())).build();
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
