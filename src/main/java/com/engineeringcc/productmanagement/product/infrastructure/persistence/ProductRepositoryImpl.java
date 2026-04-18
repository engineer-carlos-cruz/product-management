package com.engineeringcc.productmanagement.product.infrastructure.persistence;

import com.engineeringcc.productmanagement.product.domain.Product;
import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import com.engineeringcc.productmanagement.product.infrastructure.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductRepositoryImpl.class);
    private final Map<Long, ProductEntity> products = new HashMap<>();
    private final ProductMapper mapper;

    public ProductRepositoryImpl(ProductMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(Product product) {
        ProductEntity entity = mapper.toProductEntity(product);
        products.put(entity.getId(), entity);
    }

    @Override
    public List<Product> findAll() {
        return products.values()
                .stream()
                .map(mapper::toProduct)
                .toList();
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Product> findById(Long id) {
        log.info("Finding product with id {}", id);
        Product product = mapper.toProduct(products.get(id));
        return Optional.ofNullable(product);
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }

    @Override
    public int size() {
        return products.size();
    }
}
