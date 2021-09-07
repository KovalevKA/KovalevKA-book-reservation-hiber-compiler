package com.example.bookreservationhibercompiler.repository;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.entity.Author;
import com.example.bookreservationhibercompiler.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommonRepositoryImplTest {

    private final String TEST_AUTHOR_NAME = "test_author_name";
    private final String TEST_AUTHOR_NAME_FOR_UPDATE = "test_author_name_1";

    @Autowired
    private CommonRepository<Author> repository;

    @Autowired
    private AuthorServiceImpl authorService;

    private Long id;

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
        Author author = repository.findOne(id, Author.class);

        assertNotNull(author);
    }

    @Test
    void findAll() {
        List<Author> authors = repository.findAll(Author.class);

        assertNotNull(authors);
    }

    @Test
    void create() {
        Author author = new Author();
        author.setName(TEST_AUTHOR_NAME);
        Author authorForCheck = repository.create(author);

        Assertions.assertAll(
                () -> assertNotNull(authorForCheck),
                () -> assertEquals(author, authorForCheck)
        );
    }

    @Test
    @Transactional
    void update() {
        Author author = repository.findOne(id, Author.class);

        author.setName(TEST_AUTHOR_NAME_FOR_UPDATE);

        Author authorForCheck = repository.update(author);

        Assertions.assertAll(
                () -> assertNotNull(authorForCheck),
                () -> assertEquals(author, authorForCheck)
        );
    }

    @Test
    void delete() {
        Author author = repository.findOne(id, Author.class);

        repository.delete(author);

        assertNull(repository.findOne(id, Author.class));
    }

    @Test
    void deleteById() {
        repository.deleteById(id, Author.class);

        assertNull(repository.findOne(id, Author.class));
    }
}