package io.github.soloyolo;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.dropwizard.Application;
import io.dropwizard.jetty.setup.ServletEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class App extends Application<AppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "tasks-app";
    }

    @Override
    public void initialize(final Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.getObjectMapper().registerModule(new JavaTimeModule())
                .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(final AppConfiguration configuration, final Environment environment) {
        final TasksDao dao = new InMemoryTasksDao();
        final TasksResource resource = new TasksResource(dao);
        environment.jersey().register(resource);
        environment.jersey().register(new AppExceptionMapper());
        enableCors(environment.servlets());
    }

    private void enableCors(ServletEnvironment servlets) {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors = servlets.addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
