package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.ClientDTO;
import com.example.bookreservationhibercompiler.dto.ReservDTO;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCancelReservation;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCheckReservedBooksByBookId;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForMakeReservetion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReservServiceImplTest {

    @Autowired
    private ReservServiceImpl reservService = new ReservServiceImpl();

    @Autowired
    private ClientServiceImpl clientService = new ClientServiceImpl();

    @Autowired
    private BookServiceImpl bookService = new BookServiceImpl();

    @Test
    void getReservationClientListById() {
        ClientDTO clientDTO = clientService.findAll().stream().findFirst().get();

        List<ReservDTO> reservDTOList = reservService.getReservationClientListById(clientDTO.getId());

        assertNotNull(reservDTOList);
    }

    @Test
    void checkReservedBooksByBookId() {
        RequestParamForCheckReservedBooksByBookId param = new RequestParamForCheckReservedBooksByBookId();

        param.setListBooksId(bookService.findAll().stream().map(value -> value.getId()).collect(Collectors.toList()));

        List<ReservDTO> reservDTOS = reservService.checkReservedBooksByBookId(param);

        assertNotNull(reservDTOS);
    }

    @Test
    void make() {
        RequestParamForMakeReservetion param = new RequestParamForMakeReservetion();
        LocalDate dateTo = LocalDate.of(2022, 01, 01);
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        param.setClientId(clientService.findAll().stream().findFirst().get().getId());
        param.setListBooksId(ids);
        param.setDateTo(dateTo);

        Assertions.assertThrows(NullPointerException.class, ()->{
            reservService.make(param);
        });
    }

    @Test
    void cancel() {
        RequestParamForCancelReservation param = new RequestParamForCancelReservation();
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        param.setClientId(clientService.findAll().stream().findFirst().get().getId());
        param.setListReservId(ids);

        Integer count = reservService.cancel(param);

        assertEquals(1, count);

    }
}