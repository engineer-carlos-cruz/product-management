package com.engineeringcc.productmanagement.product.application.query.getProductById;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import com.engineeringcc.productmanagement.product.domain.ProductNotFoundException;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import com.engineeringcc.productmanagement.product.infrastructure.ProductMapper;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest, ProductResponse> {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public GetProductByIdHandler(ProductRepository productRepository, ProductMapper productMapper) {
        this.repository = productRepository;
        this.mapper = productMapper;
    }

    @Override
    public ProductResponse handle(GetProductByIdRequest request) {
        return repository.findById(request.id())
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(request.id().toString()));
    }

    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }
}
