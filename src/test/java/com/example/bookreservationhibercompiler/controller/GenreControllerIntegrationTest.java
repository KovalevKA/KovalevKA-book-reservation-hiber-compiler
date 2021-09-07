package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import com.example.bookreservationhibercompiler.service.GenreService;
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
import static org.junit.Assert.assertTrue;

@Category(IntegrationTest.class)
@SpringBootTest
class GenreControllerIntegrationTest {

    private static String URI_CONTROLLER = "http://localhost:8083/api/genres";
    private static String TEST_NAME_FOR_GENRE = "test_genre";
    private static String TEST_NAME_FOR_GENRE_FOR_CHANGE = "test_genre_1";

    @Autowired
    private GenreService genreService;

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    private Long id;

    @BeforeEach
    void newEntityForTest() {
        GenreDTO dto = new GenreDTO();
        dto.setName(TEST_NAME_FOR_GENRE);
        dto = genreService.create(dto);
        id = dto.getId();
    }

    @AfterEach
    void deleteEntityForTest() {
        try {
            genreService.deleteById(id);
        } finally {
            return;
        }
    }

    @Test
    void getAllGenres() {
        ResponseEntity<List> response =
                restTemplate.getForEntity(URI_CONTROLLER, List.class);

        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertTrue(!response.getBody().isEmpty())
        );
    }

    @Test
    void getGenreByNameLike() {
        String uri = URI_CONTROLLER + "/find-name-like?name={name}";
        ResponseEntity<List> response =
                restTemplate.getForEntity(uri, List.class, TEST_NAME_FOR_GENRE);

        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    @Transactional
    void addGenre() {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(TEST_NAME_FOR_GENRE);
        ResponseEntity<GenreDTO> response =
                restTemplate.postForEntity(URI_CONTROLLER, genreDTO, GenreDTO.class);

        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(genreDTO, response.getBody())
        );
    }

    @Test
    void editGenre() {
        GenreDTO dto = new GenreDTO();
        dto.setName(TEST_NAME_FOR_GENRE_FOR_CHANGE);

        String uriForEdit = URI_CONTROLLER + "/" + id;

        assertEquals(dto, restTemplate.patchForObject(uriForEdit, dto, GenreDTO.class));
    }

    @Test
    void deleteGenre() {
        String uriForEdit = URI_CONTROLLER + "/{id}";
        restTemplate.delete(uriForEdit, id);
    }
}