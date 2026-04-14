package com.engineeringcc.productmanagement.product.application.query.getProductById;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import com.engineeringcc.productmanagement.product.domain.ProductNotFoundException;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import com.engineeringcc.productmanagement.product.infrastructure.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, ProductResponse> {

    private static final Logger log = LoggerFactory.getLogger(GetProductByIdHandler.class);
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public GetProductByIdHandler(ProductRepository productRepository, ProductMapper productMapper) {
        this.repository = productRepository;
        this.mapper = productMapper;
    }

    @Override
    public ProductResponse handle(GetProductByIdRequest request) {
        log.info("Getting product with id {}...", request.id());
        ProductResponse response = repository.findById(request.id())
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(request.id().toString()));
        log.info("Found product with id {}.", request.id());
        return response;
    }

    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
