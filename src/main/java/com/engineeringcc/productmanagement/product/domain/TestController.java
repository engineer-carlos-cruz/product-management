package com.engineeringcc.productmanagement.product.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "test")
public class TestController {

    private record ProductRequest(
            Long id,
            String name,
            String description
    ) {}

    @PostMapping
    public ResponseEntity<String> testProduct(@RequestBody ProductRequest request) {
        try {
            Product product = Product.builder()
                    .id(request.id)
                    .name(request.name)
                    .description(request.description)
                    .build();

            return ResponseEntity.ok(product.toString());

        } catch (IllegalStateException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
