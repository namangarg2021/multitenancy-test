package com.dlt.services;

import com.dlt.entities.Product;
import com.dlt.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Transactional
    public void createProduct(Product product) {
        productRepository.persist(product);
    }
}
