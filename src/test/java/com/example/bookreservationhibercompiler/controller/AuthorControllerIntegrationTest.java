package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.service.AuthorService;
import org.apache.kafka.test.IntegrationTest;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Category(IntegrationTest.class)
@SpringBootTest
class AuthorControllerIntegrationTest {

    private static String URI_CONTROLLER = "http://localhost:8083/api/authors";
    private static String TEST_NAME_FOR_AUTHOR = "test_author";
    private static String TEST_NAME_FOR_AUTHOR_FOR_CHANGE = "test_author_1";

    @Autowired
    private AuthorService authorService;

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    private Long id;

    @BeforeEach
    void newEntityForTest() {
        AuthorDTO dto = new AuthorDTO();
        dto.setName(TEST_NAME_FOR_AUTHOR);
        dto = authorService.create(dto);
        id = dto.getId();
    }

    @AfterEach
    void deleteEntityForTest() {
        try {
            authorService.deleteById(id);
        } finally {
            return;
        }
    }

    @Test
    void getAllAuthors() throws Exception {
        ResponseEntity<List> response =
                restTemplate.getForEntity(URI_CONTROLLER, List.class);

        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(!response.getBody().isEmpty())
        );
    }

    @Test
    void getAuthorByNameLike() {
        String uri = URI_CONTROLLER + "/find-name-like?name={name}";
        ResponseEntity<List> response =
                restTemplate.getForEntity(uri, List.class, TEST_NAME_FOR_AUTHOR);

        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    @Transactional
    void addAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(TEST_NAME_FOR_AUTHOR);
        ResponseEntity<AuthorDTO> response =
                restTemplate.postForEntity(URI_CONTROLLER, authorDTO, AuthorDTO.class);

        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(authorDTO, response.getBody())
        );
    }

    @Test
    void editAuthor() {
        AuthorDTO dto = new AuthorDTO();
        dto.setName(TEST_NAME_FOR_AUTHOR_FOR_CHANGE);

        String uriForEdit = URI_CONTROLLER + "/" + id;

        assertEquals(dto, restTemplate.patchForObject(uriForEdit, dto, AuthorDTO.class));
    }

    @Test
    void deleteAuthor() {
        String uriForEdit = URI_CONTROLLER + "/{id}";
        restTemplate.delete(uriForEdit, id);
    }
}