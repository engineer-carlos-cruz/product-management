package com.engineeringcc.productmanagement.product.infrastructure;

import com.engineeringcc.productmanagement.common.mediator.Mediator;
import com.engineeringcc.productmanagement.product.application.command.createProduct.CreateProductRequest;
import com.engineeringcc.productmanagement.product.application.command.deleteProduct.DeleteProductRequest;
import com.engineeringcc.productmanagement.product.application.command.updateProduct.UpdateProductRequest;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import com.engineeringcc.productmanagement.product.application.query.getAllProducts.GetAllProductsRequest;
import com.engineeringcc.productmanagement.product.application.query.getProductById.GetProductByIdRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final Mediator mediator;

    public ProductController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        log.info("Getting all products...");
        List<ProductResponse> products = mediator.dispatch(new GetAllProductsRequest());
        log.info("Found {} products.", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProductResponse> getById(@Valid @PathVariable Long id) {
        log.info("Getting product with id {}...", id);
        GetProductByIdRequest request = new GetProductByIdRequest(id);
        log.info("Found product with id {}.", id);
        return ResponseEntity.ok(mediator.dispatch(request));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @ModelAttribute CreateProductRequest request) {
        log.info("Creating new product...");
        mediator.dispatch(request);
        log.info("New product was created successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @ModelAttribute UpdateProductRequest request) {
        log.info("Updating product...");
        mediator.dispatch(request);
        log.info("Product updated successfully.");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@Valid @PathVariable Long id) {
        log.info("Deleting product with id {}...", id);
        DeleteProductRequest request = new DeleteProductRequest(id);
        log.info("Product with id {} was deleted.", id);
        mediator.dispatchAsync(request);
    }
}
