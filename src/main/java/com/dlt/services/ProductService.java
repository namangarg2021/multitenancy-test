package com.dlt.services;

import com.dlt.entities.Product;
import com.dlt.repository.ProductRepository;
import com.dlt.restclient.ExternalApiClient;
import com.dlt.tenant.TenantContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Inject
    @RestClient
    ExternalApiClient externalApiClient;

    @Transactional
    public void createProduct(Product product) {
        productRepository.persist(product);
    }

    public String fetchProductsForTenant(String tenantId) {
        try {
            // Set the tenant ID in the context
            TenantContext.setCurrentTenant(tenantId);

            // Make the REST client call
            return externalApiClient.getProducts();
        } finally {
            // Clear the tenant ID from the context
            TenantContext.clear();
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().list();
    }
}
