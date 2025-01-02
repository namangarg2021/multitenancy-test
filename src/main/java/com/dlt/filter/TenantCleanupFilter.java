package com.dlt.filter;

import com.dlt.tenant.TenantContext;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TenantCleanupFilter implements ContainerResponseFilter {
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
		TenantContext.clear();
	}
}