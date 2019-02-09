package com.afferolab.apptest.resources;

import com.afferolab.apptest.api.Product;
import com.afferolab.apptest.dao.ProductDAO;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private ProductDAO productDAO;

    public ProductResource(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GET
    @UnitOfWork
    public List<Product> findByName() {
        return productDAO.findAll();
    }

    @POST
    @UnitOfWork
    public void createProduct(Product product) {
        productDAO.createOrUpdate(product);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<Product> findById(@PathParam("id") LongParam id) {
        return productDAO.findById(id.get());
    }
}