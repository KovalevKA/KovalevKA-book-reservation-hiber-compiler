package com.example.bookreservationhibercompiler.config;

import com.example.bookreservationhibercompiler.controller.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init() {
        register(AuthorController.class);
        register(BookController.class);
        register(ClientController.class);
        register(GenreController.class);
        register(ReservController.class);
        register(TranslatorController.class);
    }
}
