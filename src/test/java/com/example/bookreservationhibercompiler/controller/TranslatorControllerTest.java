package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.example.bookreservationhibercompiler.service.TranslatorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
class TranslatorControllerTest {

    private static String URI_CONTROLLER = "http://localhost:8083/api/translators";
    private static String TEST_NAME_FOR_TRANSLATOR = "test_translator";
    private static String TEST_NAME_FOR_TRANSLATOR_FOR_CHANGE = "test_translator_1";

    @Autowired
    private TranslatorService translatorService;

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    private Long id;


    @BeforeEach
    void newEntityForTest() {
        TranslatorDTO dto = new TranslatorDTO();
        dto.setName(TEST_NAME_FOR_TRANSLATOR);
        dto = translatorService.create(dto);
        id = dto.getId();
    }

    @AfterEach
    void deleteEntityForTest() {
        try {
            translatorService.deleteById(id);
        }finally {
            return;
        }
    }

    @Test
    void testGetAllTranslators() {
        ResponseEntity<List> response =
                restTemplate.getForEntity(URI_CONTROLLER, List.class);
        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(!response.getBody().isEmpty())
        );
    }

    @Test
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
    void testGetTranslatorsByNameLike() {
        String uri = URI_CONTROLLER + "/find-name-like?name={name}";
        ResponseEntity<List> response =
                restTemplate.getForEntity(uri, List.class, TEST_NAME_FOR_TRANSLATOR);
        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    void testEditTranslator() {
        TranslatorDTO dto = new TranslatorDTO();
        dto.setName(TEST_NAME_FOR_TRANSLATOR_FOR_CHANGE);

        String uriForEdit = URI_CONTROLLER + "/" + id;

        assertEquals(dto, restTemplate.patchForObject(uriForEdit, dto, TranslatorDTO.class));
    }

    @Test
    void testDeleteTranslator() {
        String uriForEdit = URI_CONTROLLER + "/{id}";
        restTemplate.delete(uriForEdit, id);
    }
}