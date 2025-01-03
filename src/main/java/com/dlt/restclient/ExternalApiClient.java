package com.dlt.restclient;

import com.dlt.filter.TenantClientRequestFilter;
import io.quarkus.oidc.client.reactive.filter.OidcClientRequestReactiveFilter;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/investment-planning/products")
@RegisterRestClient(configKey = "external-api")
@RegisterProvider(OidcClientRequestReactiveFilter.class)
@RegisterProvider(TenantClientRequestFilter.class)
public interface ExternalApiClient {

    @GET
    Response getProducts();
}

