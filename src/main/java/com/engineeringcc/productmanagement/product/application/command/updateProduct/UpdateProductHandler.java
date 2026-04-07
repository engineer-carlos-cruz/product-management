package com.engineeringcc.productmanagement.product.application.command.updateProduct;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.common.util.File;
import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private final ProductRepository repository;
    private final File file;

    public UpdateProductHandler(ProductRepository productRepository, File file) {
        this.repository = productRepository;
        this.file = file;
    }

    @Override
    public Void handle(UpdateProductRequest request) {
        String uniqueFileName = file.saveImage(request.file());

        Product product = Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .image(uniqueFileName)
                .build();

        repository.save(product);
        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
