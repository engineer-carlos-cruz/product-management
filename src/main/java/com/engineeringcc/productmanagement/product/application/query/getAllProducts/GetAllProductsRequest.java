package com.engineeringcc.productmanagement.product.application.query.getAllProducts;

import com.engineeringcc.productmanagement.common.mediator.Request;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;

import java.util.List;

public record GetAllProductsRequest() implements Request<List<ProductResponse>> {
}
