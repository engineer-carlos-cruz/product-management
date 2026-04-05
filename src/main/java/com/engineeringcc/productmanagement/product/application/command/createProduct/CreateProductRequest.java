package com.engineeringcc.productmanagement.product.application.command.createProduct;

import com.engineeringcc.productmanagement.common.mediator.Request;
import jakarta.validation.constraints.*;

public record CreateProductRequest(
        @NotBlank(message = "The name cannot be empty.")
        String name,

        @Size(max = 2, message = "The description must be no more than 200 characters long.")
        String description,

        @DecimalMax(value = "999999.99", message = "The maximum price is 99999.99")
        @DecimalMin(value = "50.00", message = "The minimum price is $50.00.")
        Double price,

        @NotBlank(message = "The image cannot be empty.")
        String image
) implements Request<Void> {}
