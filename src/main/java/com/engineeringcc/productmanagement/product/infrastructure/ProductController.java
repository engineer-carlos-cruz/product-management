package com.engineeringcc.productmanagement.product.infrastructure;

import com.engineeringcc.productmanagement.common.mediator.Mediator;
import com.engineeringcc.productmanagement.product.application.command.createProduct.CreateProductRequest;
import com.engineeringcc.productmanagement.product.application.command.deleteProduct.DeleteProductRequest;
import com.engineeringcc.productmanagement.product.application.command.updateProduct.UpdateProductRequest;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import com.engineeringcc.productmanagement.product.application.query.getAllProducts.GetAllProductsRequest;
import com.engineeringcc.productmanagement.product.application.query.getProductById.GetProductByIdRequest;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(mediator.dispatch(new GetAllProductsRequest()));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProductResponse> getById(@Valid @PathVariable Long id) {
        GetProductByIdRequest request = new GetProductByIdRequest(id);
        return ResponseEntity.ok(mediator.dispatch(request));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @ModelAttribute CreateProductRequest request) {
        mediator.dispatch(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @ModelAttribute UpdateProductRequest request) {
        mediator.dispatch(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@Valid @PathVariable Long id) {
        DeleteProductRequest request = new DeleteProductRequest(id);
        mediator.dispatch(request);
    }
}
