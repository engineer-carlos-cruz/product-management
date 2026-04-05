package com.engineeringcc.productmanagement.product.infrastructure;

import com.engineeringcc.productmanagement.product.application.query.ProductResponse;
import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.infrastructure.persistence.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "price", target = "unitPrice"),
            @Mapping(source = "image", target = "urlImage")
    })
    ProductResponse toProductResponse(Product product);

    ProductEntity toProductEntity(Product product);
    Product toProduct(ProductEntity entity);
}
