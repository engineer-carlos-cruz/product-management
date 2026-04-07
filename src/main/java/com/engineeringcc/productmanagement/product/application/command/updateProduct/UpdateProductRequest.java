package com.engineeringcc.productmanagement.product.application.command.updateProduct;

import com.engineeringcc.productmanagement.common.mediator.Request;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public record UpdateProductRequest(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @Size(max = 200)
        String description,

        @DecimalMax(value = "999999.99")
        @DecimalMin(value = "50.00")
        Double price,

        @NotNull
        MultipartFile file
) implements Request<Void> {}
