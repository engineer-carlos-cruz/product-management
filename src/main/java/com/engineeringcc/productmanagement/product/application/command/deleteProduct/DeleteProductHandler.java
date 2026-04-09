package com.engineeringcc.productmanagement.product.application.command.deleteProduct;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository repository;

    public DeleteProductHandler(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Void handle(DeleteProductRequest request) {
        System.out.println("Deleting product with id "
                + request.id()
                + "... / "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        try {
            Thread.sleep(10000);
            repository.deleteById(request.id());
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }

        System.out.println("Product deleted successfully! / "
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
