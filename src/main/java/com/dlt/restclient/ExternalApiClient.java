package com.dlt.restclient;

import com.dlt.filter.TenantClientRequestFilter;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/products")
@RegisterRestClient(configKey = "external-api")
@RegisterProvider(TenantClientRequestFilter.class)
public interface ExternalApiClient {

    @GET
    String getProducts();
}

