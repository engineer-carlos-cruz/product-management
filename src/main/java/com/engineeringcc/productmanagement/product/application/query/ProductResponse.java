package com.engineeringcc.productmanagement.product.application.query;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double unitPrice,
        String urlImage
) {}
