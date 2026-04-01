package com.engineeringcc.productmanagement.product.application.command.updateProduct;

import com.engineeringcc.productmanagement.common.mediator.Request;

public record UpdateProductRequest(
        Long id,
        String name,
        String description,
        Double price,
        String image
) implements Request<Void> {}
