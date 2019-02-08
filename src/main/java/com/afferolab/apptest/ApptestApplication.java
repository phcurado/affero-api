package com.afferolab.apptest;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ApptestApplication extends Application<ApptestConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ApptestApplication().run(args);
    }

    @Override
    public String getName() {
        return "Apptest";
    }

    @Override
    public void initialize(final Bootstrap<ApptestConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ApptestConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
