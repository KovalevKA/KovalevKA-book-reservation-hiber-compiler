package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AuthorServiceImplTest {

    private final String TEST_AUTHOR_NAME = "test_author_name";

    private Long id;

    @Autowired
    private AuthorServiceImpl authorService;

    @BeforeEach
    void newEntityForTest() {
        AuthorDTO dto = new AuthorDTO();
        dto.setName(TEST_AUTHOR_NAME);
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
    void testGetByNameLike() {
        List<AuthorDTO> authors = authorService.getByNameLike(TEST_AUTHOR_NAME);

        assertNotNull(authors);
    }
}