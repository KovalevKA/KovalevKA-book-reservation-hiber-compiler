package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AuthorServiceImplTest {

    private final String TEST_AUTHOR_NAME = "test_author_name";
    private final String TEST_AUTHOR_NAME_FOR_UPDATE = "test_author_name_1";

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
    void findOne() {
        AuthorDTO authorDTOForCheck = authorService.findOne(id);

        assertNotNull(authorDTOForCheck);
    }

    @Test
    void findAll() {
        List<AuthorDTO> dtos = authorService.findAll();

        assertNotNull(dtos);
    }

    @Test
    void create() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(TEST_AUTHOR_NAME);

        AuthorDTO authorDTOForCheck = authorService.create(authorDTO);

        assertEquals(authorDTO, authorDTOForCheck);
    }

    @Test
    @Disabled
    void update() {
        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setId(id);
        authorDTO.setName(TEST_AUTHOR_NAME_FOR_UPDATE);

        assertEquals(authorDTO, authorService.update(id, authorDTO));
    }

    @Test
    void delete() {
        AuthorDTO author = new AuthorDTO();
        authorService.delete(author);
    }

    @Test
    void deleteById() {
        authorService.deleteById(id);

    }

    @Test
    void getByNameLike() {
        AuthorDTO authorDTOForCHeck = authorService.getByNameLike(TEST_AUTHOR_NAME).stream().findFirst().get();

        assertNotNull(authorDTOForCHeck);
    }
}