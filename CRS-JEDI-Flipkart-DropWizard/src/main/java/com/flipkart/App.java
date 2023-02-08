package com.flipkart;

/**
 * Hello world!
 *
 */
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipkart.controller.*;


/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
    ;

    /*
     * It is a dropwizard initialize method which will initialize the configuration.
     * */
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    /*
     * Run will execute by the dropwizard container and registered all the web services here.
     * */

    @Override
    public void run(Configuration c, Environment e) {
        e.jersey().register(new AdminRESTAPI());
        e.jersey().register(new CRSApplicationRESTAPI());
        e.jersey().register(new ProfessorRESTAPI());
        e.jersey().register(new StudentRESTAPI());
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}