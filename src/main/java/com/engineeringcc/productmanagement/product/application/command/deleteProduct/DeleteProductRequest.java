package com.engineeringcc.productmanagement.product.application.command.deleteProduct;

import com.engineeringcc.productmanagement.common.mediator.Request;

public record DeleteProductRequest(Long id) implements Request<Void> {}
