package com.dlt.tenant;

import com.dlt.entities.Product;
import com.dlt.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TenantService {
	
	@Inject
	ProductRepository productRepository;
	
	public String[] getAllTenantIds() {
		return new String[]{"tenant1", "tenant2"};
	}
	
	@Transactional
	public void processTenantJob(String tenantId) {
		System.out.println("Processing background job for tenant: " + tenantId);
		List<Product> products = productRepository.findAll().list();
		for(Product product : products) {
			product.setName(tenantId);
			productRepository.persist(product);
		}
	}
	
	@Transactional
	public void addProduct(Product product) {
		productRepository.persist(product);
	}
}
