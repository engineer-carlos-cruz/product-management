package com.engineeringcc.productmanagement.product.application.scheduling;

import com.engineeringcc.productmanagement.product.domain.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FixProductPriceSchedule {

    private static final Logger log = LoggerFactory.getLogger(FixProductPriceSchedule.class);
    private final ProductRepository repository;

    public FixProductPriceSchedule(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void fixProductsPrice() {
        log.warn("Fixing price by all products...");

        repository.findAll().forEach(product -> {
            product.setPrice(product.getPrice() * 1.1);
            repository.save(product);
        });

        log.info("Finished fixing products price.");
    }
}
