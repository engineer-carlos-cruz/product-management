package com.engineeringcc.productmanagement.product.application.command.createProduct;

import com.engineeringcc.productmanagement.common.mediator.Request;

public record CreateProductRequest(
        String name,
        String description,
        Double price,
        String image
) implements Request<Void> {}
