package com.engineeringcc.productmanagement.product.application.query.getProductById;

import com.engineeringcc.productmanagement.common.mediator.Request;
import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import jakarta.validation.constraints.NotNull;

public record GetProductByIdRequest(@NotNull Long id) implements Request<ProductResponse> {}
