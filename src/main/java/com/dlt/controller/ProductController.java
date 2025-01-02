package com.dlt.controller;

import com.dlt.entities.Product;
import com.dlt.services.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/{tenant}/products")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService service;

    @POST
    public Response createProduct(@PathParam("tenant") String tenant, Product product) {
        service.createProduct(product);
        return Response.ok().entity(product).build();
    }
}
