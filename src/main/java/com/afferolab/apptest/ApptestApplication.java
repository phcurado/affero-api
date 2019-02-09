package com.afferolab.apptest;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import com.afferolab.apptest.api.Product;
import com.afferolab.apptest.dao.ProductDAO;
import com.afferolab.apptest.resources.ProductResource;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        configureCors(environment);
        final ProductDAO dao = new ProductDAO(hibernate.getSessionFactory());
        environment.jersey().register(new ProductResource(dao));
    }

    private void configureCors(Environment environment) {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
                "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    }

}
