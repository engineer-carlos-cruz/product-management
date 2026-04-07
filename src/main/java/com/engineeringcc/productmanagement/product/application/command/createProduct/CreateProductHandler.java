package com.engineeringcc.productmanagement.product.application.command.createProduct;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.common.util.File;
import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private final ProductRepository repository;
    private final File file;

    public CreateProductHandler(ProductRepository productRepository, File file) {
        this.repository = productRepository;
        this.file = file;
    }

    @Override
    public Void handle(CreateProductRequest request) {
        String uniqueFileName = file.saveImage(request.file());

        Product product = Product.builder()
                .id((long) repository.size() + 1)
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .image(uniqueFileName)
                .build();

        repository.save(product);
        return null;
    }

    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
