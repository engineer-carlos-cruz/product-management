package com.engineeringcc.productmanagement.product.application.query.getAllProducts;

import com.engineeringcc.productmanagement.common.mediator.RequestHandler;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import com.engineeringcc.productmanagement.product.infrastructure.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsHandler implements RequestHandler<GetAllProductsRequest, List<ProductResponse>> {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public GetAllProductsHandler(ProductRepository productRepository, ProductMapper productMapper) {
        this.repository = productRepository;
        this.mapper = productMapper;
    }

    @Override
    public List<ProductResponse> handle(GetAllProductsRequest request) {
        return repository.findAll().stream().map(mapper::toProductResponse).toList();
    }

    @Override
    public Class<GetAllProductsRequest> getRequestType() {
        return GetAllProductsRequest.class;
    }
}
