package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TranslatorServiceImplTest {

    private final String TEST_TRANSLATOR_NAME = "test_author_name";

    private Long id;

    @Autowired
    private TranslatorServiceImpl translatorService;

    @BeforeEach
    void newEntityForTest() {
        TranslatorDTO dto = new TranslatorDTO();
        dto.setName(TEST_TRANSLATOR_NAME);
        dto = translatorService.create(dto);
        id = dto.getId();
    }

    @AfterEach
    void deleteEntityForTest() {
        try {
            translatorService.deleteById(id);
        } finally {
            return;
        }
    }

    @Test
    void getByNameLike() {
        List<TranslatorDTO> dtos = translatorService.getByNameLike(TEST_TRANSLATOR_NAME);

        assertNotNull(dtos);
    }
}