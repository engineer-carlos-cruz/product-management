package com.engineeringcc.productmanagement.product.infrastructure;

import com.engineeringcc.productmanagement.common.mediator.Mediator;
import com.engineeringcc.productmanagement.product.application.CreateProductRequest;
import com.engineeringcc.productmanagement.product.application.UpdateProductRequest;
import com.engineeringcc.productmanagement.product.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "products")
public class ProductController {

    private final Mediator mediator;

    public ProductController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(Product.builder().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateProductRequest request) {
        mediator.dispatch(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateProductRequest request) {
        mediator.dispatch(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
    }
}
