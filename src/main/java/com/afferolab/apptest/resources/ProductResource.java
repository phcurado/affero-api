package com.afferolab.apptest.resources;

import com.afferolab.apptest.api.Product;
import com.afferolab.apptest.dao.ProductDAO;
import com.google.common.base.Optional;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") LongParam id) {
        Optional<Product> product = productDAO.findById(id.get());
        if (product != null) {
            productDAO.delete(product.get());
        }
    }
}