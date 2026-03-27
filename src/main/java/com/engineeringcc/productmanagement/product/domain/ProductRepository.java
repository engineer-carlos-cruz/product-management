package com.engineeringcc.productmanagement.product.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void deleteById(Long id);
}
