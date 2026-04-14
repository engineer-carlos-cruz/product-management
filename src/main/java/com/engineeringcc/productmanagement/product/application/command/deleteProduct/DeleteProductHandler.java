package com.engineeringcc.productmanagement.product.application.command.deleteProduct;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private static final Logger log = LoggerFactory.getLogger(DeleteProductHandler.class);
    private final ProductRepository repository;

    public DeleteProductHandler(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Void handle(DeleteProductRequest request) {
        log.info("Deleting product with id {}...", request.id());

        try {
            Thread.sleep(10000);
            repository.deleteById(request.id());
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }

        log.info("Product with id {} was deleted.", request.id());

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
