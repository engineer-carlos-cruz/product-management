package com.engineeringcc.productmanagement.product.application;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private final ProductRepository repository;

    public UpdateProductHandler(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Void handle(UpdateProductRequest request) {
        Product product = Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .image(request.image())
                .build();

        repository.save(product);
        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
