package com.engineeringcc.productmanagement.product.application.command.createProduct;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.common.util.FileUtil;
import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private final ProductRepository repository;
    private final FileUtil fileUtil;

    public CreateProductHandler(ProductRepository productRepository, FileUtil fileUtil) {
        this.repository = productRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public Void handle(CreateProductRequest request) {
        String uniqueFileName = fileUtil.saveImage(request.file());

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
