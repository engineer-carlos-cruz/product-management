package com.engineeringcc.productmanagement.product.application.query.getProductById;

import com.engineeringcc.productmanagement.common.mediator.Request;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;

public record GetProductByIdRequest(Long id) implements Request<ProductResponse> {}
