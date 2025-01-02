package com.dlt.filter;

import com.dlt.tenant.TenantContext;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TenantRequestFilter implements ContainerRequestFilter {
	
	@Override
	public void filter(ContainerRequestContext requestContext) {
		// Extract tenant ID from HTTP header
		String tenantId = requestContext.getHeaderString("X-Tenant-ID");
		if (tenantId == null) {
			throw new IllegalArgumentException("Tenant ID is missing");
		}
		TenantContext.setCurrentTenant(tenantId); // Store in ThreadLocal
	}
}