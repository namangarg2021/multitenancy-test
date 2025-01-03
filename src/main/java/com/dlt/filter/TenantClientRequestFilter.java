package com.dlt.filter;

import com.dlt.tenant.TenantContext;
import jakarta.annotation.Priority;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(2000)
public class TenantClientRequestFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) {
        String tenantId = TenantContext.getCurrentTenantId();
        if (tenantId != null) {
            // Add the tenant ID to the request headers
            requestContext.getHeaders().add("X-Tenant-ID", tenantId);
        }
    }
}
