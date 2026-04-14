package com.engineeringcc.productmanagement.product.application.command.updateProduct;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.common.util.FileUtil;
import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private static final Logger log = LoggerFactory.getLogger(UpdateProductHandler.class);
    private final ProductRepository repository;
    private final FileUtil fileUtil;

    public UpdateProductHandler(ProductRepository productRepository, FileUtil fileUtil) {
        this.repository = productRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public Void handle(UpdateProductRequest request) {
        log.info("Updating product...");
        String uniqueFileName = fileUtil.saveImage(request.file());

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
