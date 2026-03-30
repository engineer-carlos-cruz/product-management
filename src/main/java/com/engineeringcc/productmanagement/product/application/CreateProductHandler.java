package com.engineeringcc.productmanagement.product.application;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private final ProductRepository repository;

    public CreateProductHandler(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Void handle(CreateProductRequest request) {
        Product product = Product.builder()
                .id((long) repository.size() + 1)
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .image(request.image())
                .build();

        repository.save(product);
        return null;
    }

    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
