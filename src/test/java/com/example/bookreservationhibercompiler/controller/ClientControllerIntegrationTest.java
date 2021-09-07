package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.ClientDTO;
import com.example.bookreservationhibercompiler.service.ClientService;
import org.apache.kafka.test.IntegrationTest;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Category(IntegrationTest.class)
@SpringBootTest
class ClientControllerIntegrationTest {

    private static String URI_CONTROLLER = "http://localhost:8083/api/clients";
    private static String TEST_NAME_FOR_CLIENT = "test_client";
    private static String TEST_NAME_FOR_CLIENT_FOR_CHANGE = "test_client_1";

    @Autowired
    private ClientService clientService;

    RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

    private Long id;


    @BeforeEach
    void newEntityForTest() {
        ClientDTO dto = new ClientDTO();
        dto.setName(TEST_NAME_FOR_CLIENT);
        dto = clientService.create(dto);
        id = dto.getId();
    }

    @AfterEach
    void deleteEntityForTest() {
        try {
            clientService.deleteById(id);
        } finally {
            return;
        }
    }


    @Test
    @Disabled
    void getClientByParams() {
        String uri = URI_CONTROLLER + "/find-name-like?name={name}";
        ResponseEntity<List> response =
                restTemplate.getForEntity(uri, List.class, TEST_NAME_FOR_CLIENT);

        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    void addClient() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName(TEST_NAME_FOR_CLIENT);
        ResponseEntity<ClientDTO> response =
                restTemplate.postForEntity(URI_CONTROLLER, clientDTO, ClientDTO.class);

        Assertions.assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(clientDTO, response.getBody())
        );
    }

    @Test
    void editClient() {
        ClientDTO dto = new ClientDTO();
        dto.setName(TEST_NAME_FOR_CLIENT_FOR_CHANGE);

        String uriForEdit = URI_CONTROLLER + "/" + id;

        assertEquals(dto, restTemplate.patchForObject(uriForEdit, dto, ClientDTO.class));
    }

    @Test
    void deleteClient() {
        String uriForEdit = URI_CONTROLLER + "/{id}";
        restTemplate.delete(uriForEdit, id);
    }
}