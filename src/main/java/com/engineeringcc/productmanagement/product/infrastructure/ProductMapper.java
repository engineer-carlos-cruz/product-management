package com.engineeringcc.productmanagement.product.infrastructure;

import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import com.engineeringcc.productmanagement.product.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(source = "price", target = "unitPrice"),
            @Mapping(source = "image", target = "urlImage")
    })
    ProductResponse toProductResponse(Product product);
}
