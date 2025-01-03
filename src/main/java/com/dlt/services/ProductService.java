package com.dlt.services;

import com.dlt.entities.Product;
import com.dlt.repository.ProductRepository;
import com.dlt.restclient.ExternalApiClient;
import com.dlt.tenant.TenantContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
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

    public String fetchProductsForTenant() {
        try {
            Response response = externalApiClient.getProducts();
            return response.readEntity(String.class);
        } finally {
            TenantContext.clear();
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().list();
    }
}
