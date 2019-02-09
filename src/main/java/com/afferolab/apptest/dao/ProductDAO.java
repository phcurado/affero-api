package com.afferolab.apptest.dao;

import java.util.List;

import com.afferolab.apptest.api.Product;
import com.google.common.base.Optional;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public class ProductDAO extends AbstractDAO<Product> {
    public ProductDAO(SessionFactory factory) {
        super(factory);
    }

    public List<Product> findAll() {
        return list(namedQuery("com.afferolab.apptest.core.Product.findAll"));
    }

    public Product createOrUpdate(Product product) {
        return persist(product);
    }

    public Optional<Product> findById(long id) {
        return Optional.fromNullable(get(id));
    }
}