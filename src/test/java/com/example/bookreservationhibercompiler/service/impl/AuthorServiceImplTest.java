package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.entity.Author;
import org.junit.Ignore;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class AuthorServiceImplTest {

    private final String TEST_AUTHOR_NAME = "test_author_name";

    @Autowired
    private AuthorServiceImpl authorService = new AuthorServiceImpl();

    @Test
    @Order(3)
    void findOne() {
        AuthorDTO authorDTO = authorService.findAll().stream().findFirst().get();

        AuthorDTO authorDTOForCheck = authorService.findOne(authorDTO.getId());

        assertEquals(authorDTO, authorDTOForCheck);

    }

    @Test
    @Order(2)
    void findAll() {
        List<AuthorDTO> dtos = authorService.findAll();

        assertNotNull(dtos);
    }

    @Test
    @Order(1)
    void create() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName(TEST_AUTHOR_NAME);

        AuthorDTO authorDTOForCheck = authorService.create(authorDTO);

        assertEquals(authorDTO, authorDTOForCheck);
    }

    @Test
    @Disabled
    @Order(4)
    void update() {
        AuthorDTO authorDTO = authorService.findAll().stream().findFirst().get();

        authorDTO.setName(TEST_AUTHOR_NAME);

        AuthorDTO authorDTOForCheck = authorService.update(authorDTO.getId(), authorDTO);

        assertEquals(authorDTO, authorDTOForCheck);
    }

    @Test
    @Order(5)
    void delete() {
        AuthorDTO author = new AuthorDTO();
        authorService.delete(author);
    }

    @Test
    @Order(7)
    void deleteById() {
        AuthorDTO authorDTO = authorService.findAll().stream().findFirst().get();

        authorService.deleteById(authorDTO.getId());

    }

    @Test
    @Order(6)
    void getByNameLike() {
        AuthorDTO authorDTO = authorService.create(new AuthorDTO(TEST_AUTHOR_NAME));

        AuthorDTO authorDTOForCHeck = authorService.getByNameLike(TEST_AUTHOR_NAME).stream().findFirst().get();

        assertEquals(authorDTO, authorDTOForCHeck);
    }
}