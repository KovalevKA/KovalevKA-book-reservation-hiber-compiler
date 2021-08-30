package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
class TranslatorControllerTest {

    private final String URI_CONTROLLER = "http://localhost:8083/api/translators";
    private final String TEST_NAME_FOR_TRANSLATOR = "test_translator";
    private final String TEST_NAME_FOR_TRANSLATOR_FOR_CHANGE = "test_translator_1";

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    ObjectMapper mapper = new ObjectMapper();

    @Test
    @Order(1)
    void testGetAllTranslators() {
        ResponseEntity<List> response =
                restTemplate.getForEntity(URI_CONTROLLER, List.class);
        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(!response.getBody().isEmpty())
        );
    }

    @Test
    @Order(2)
    void testAddTranslator() {
        TranslatorDTO translatorDTO = new TranslatorDTO();
        translatorDTO.setName(TEST_NAME_FOR_TRANSLATOR);
        ResponseEntity<TranslatorDTO> response =
                restTemplate.postForEntity(URI_CONTROLLER, translatorDTO, TranslatorDTO.class);
        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(translatorDTO, response.getBody())
        );
    }

    @Test
    @Order(3)
    void testGetTranslatorsByNameLike() {
        String uri = URI_CONTROLLER + "/find-name-like?name={name}";
        ResponseEntity<List> response =
                restTemplate.getForEntity(uri, List.class, TEST_NAME_FOR_TRANSLATOR);
        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    @Order(3)
    void testEditTranslator() {
        String uriForFind = URI_CONTROLLER + "/find-name-like?name={name}";
        ResponseEntity<List> response =
                restTemplate.getForEntity(uriForFind, List.class, TEST_NAME_FOR_TRANSLATOR);

        TranslatorDTO dtos = mapper.convertValue(response.getBody().stream().findFirst().get(), TranslatorDTO.class);
        dtos.setName(TEST_NAME_FOR_TRANSLATOR_FOR_CHANGE);

        String uriForEdit = URI_CONTROLLER + "/{id}";
        TranslatorDTO translatorDTO = restTemplate.patchForObject(uriForEdit, dtos, TranslatorDTO.class, dtos.getId());
        assertEquals(dtos, translatorDTO);


    }

    @Test
    @Order(4)
    void testDeleteTranslator() {
        String uriForFind = URI_CONTROLLER + "/find-name-like?name={name}";
        ResponseEntity<List> response =
                restTemplate.getForEntity(uriForFind, List.class, TEST_NAME_FOR_TRANSLATOR_FOR_CHANGE);

        TranslatorDTO dtos = mapper.convertValue(response.getBody().get(0), TranslatorDTO.class);
        String uriForEdit = URI_CONTROLLER + "/{id}";
        restTemplate.delete(uriForEdit, dtos.getId());
    }
}