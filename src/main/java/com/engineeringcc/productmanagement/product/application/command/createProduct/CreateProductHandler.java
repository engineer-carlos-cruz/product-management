package com.engineeringcc.productmanagement.product.application.command.createProduct;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.common.util.FileUtil;
import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateProductHandler implements RequestHandler<CreateProductRequest, Void> {

    private static final Logger log = LoggerFactory.getLogger(CreateProductHandler.class);
    private final ProductRepository repository;
    private final FileUtil fileUtil;

    public CreateProductHandler(ProductRepository productRepository, FileUtil fileUtil) {
        this.repository = productRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public Void handle(CreateProductRequest request) {
        log.info("Creating new product...");
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
