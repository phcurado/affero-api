package com.afferolab.apptest;

import com.afferolab.apptest.api.Product;
import com.afferolab.apptest.dao.ProductDAO;
import com.afferolab.apptest.resources.ProductResource;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi.DBIFactory;

public class ApptestApplication extends Application<ApptestConfiguration> {

    private final HibernateBundle<ApptestConfiguration> hibernate = new HibernateBundle<ApptestConfiguration>(
            Product.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ApptestConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new ApptestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Apptest";
    }

    @Override
    public void initialize(final Bootstrap<ApptestConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final ApptestConfiguration configuration, final Environment environment) {
        final ProductDAO dao = new ProductDAO(hibernate.getSessionFactory());
        environment.jersey().register(new ProductResource(dao));
    }

}
