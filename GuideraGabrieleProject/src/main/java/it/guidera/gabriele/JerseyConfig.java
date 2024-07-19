package it.guidera.gabriele;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import it.guidera.gabriele.security.JWTTokenNeededFilter;
import jakarta.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("it.guidera.gabriele");
        register(CorsFilter.class);
        register(JWTTokenNeededFilter.class);
    }
}