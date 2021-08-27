package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Application;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TranslatorControllerTest extends JerseyTest {

    private final String URI_CONTROLLER = "http://localhost:8083/api/translators";

    @Override
    protected Application configure() {
        return new ResourceConfig(TranslatorController.class);
    }

    ObjectMapper mapper = new ObjectMapper();

    private final String TEST_NAME_FOR_TRANSLATOR = "test";

    @Test
    void getAllTranslators() throws IOException {
        HttpRequest request = new HttpGet(URI_CONTROLLER);
        HttpResponse response = HttpClientBuilder
                .create()
                .build()
                .execute((HttpUriRequest) request);
        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
    }

    @Test
    void addTranslator() {
        TranslatorDTO translatorDTO = new TranslatorDTO();
        translatorDTO.setName(TEST_NAME_FOR_TRANSLATOR);
        HttpRequest request = new HttpGet(URI_CONTROLLER);

    }

    @Test
    void getTranslatorsByNameLike() {
    }

    @Test
    void editTranslator() {
    }

    @Test
    void deleteTranslator() {
    }
}