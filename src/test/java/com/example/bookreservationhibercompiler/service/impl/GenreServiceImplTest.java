package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GenreServiceImplTest {

    private final String TEST_GENRE_NAME = "test_author_name";

    private Long id;

    @Autowired
    private GenreServiceImpl genreService;

    @BeforeEach
    void newEntityForTest() {
        GenreDTO dto = new GenreDTO();
        dto.setName(TEST_GENRE_NAME);
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
    void getByNameLike() {
        List<GenreDTO> dtos = genreService.getByNameLike(TEST_GENRE_NAME);

        assertNotNull(dtos);
    }
}