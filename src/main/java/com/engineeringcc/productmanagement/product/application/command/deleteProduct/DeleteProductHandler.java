package com.engineeringcc.productmanagement.product.application.command.deleteProduct;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository repository;

    public DeleteProductHandler(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Void handle(DeleteProductRequest request) {
        repository.deleteById(request.id());
        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
