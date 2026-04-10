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
    public void fixProductsPriceSeconds() {
        log.warn("Fixing price by all products each 5 seconds...");

        repository.findAll().forEach(product -> {
            product.setPrice(product.getPrice() * 1.001);
            repository.save(product);
        });

        finishLog();
    }

    @Scheduled(cron = "0 50 17 * * *")
    public void fixProductsPriceDaily() {
        log.warn("Fixing price by all products daily at 05:50 p.m...");

        repository.findAll().forEach(product -> {
            product.setPrice(product.getPrice() * 1.01);
            repository.save(product);
        });

        finishLog();
    }

    private void finishLog() {
        log.info("Finished fixing products price.");
    }
}
