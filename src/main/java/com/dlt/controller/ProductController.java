package com.dlt.controller;

import com.dlt.entities.Product;
import com.dlt.services.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("products")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService service;

    @POST
    public Response createProduct(Product product) {
        service.createProduct(product);
        return Response.ok(product).build();
    }

    @GET
    public Response getAllProducts(){
        return Response.ok(service.getAllProducts()).build();
    }

    @GET
    @Path("/tenant")
    public Response fetchProductsForTenant(){
        return Response.ok(service.fetchProductsForTenant()).build();
    }
}
