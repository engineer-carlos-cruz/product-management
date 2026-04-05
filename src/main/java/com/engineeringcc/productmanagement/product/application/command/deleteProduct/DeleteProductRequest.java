package com.engineeringcc.productmanagement.product.application.command.deleteProduct;

import com.engineeringcc.productmanagement.common.mediator.Request;
import jakarta.validation.constraints.NotNull;

public record DeleteProductRequest(@NotNull Long id) implements Request<Void> {}
