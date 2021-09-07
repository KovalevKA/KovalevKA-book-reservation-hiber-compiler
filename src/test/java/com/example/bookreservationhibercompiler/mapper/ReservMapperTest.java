package com.example.bookreservationhibercompiler.mapper;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import com.example.bookreservationhibercompiler.entity.Reserv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

class ReservMapperTest {

    private CommonMapper<Reserv, ReservDTO> mapper = new CommonMapperImpl();

    @Test
    void toDTO() {
        Reserv reserv = new Reserv();
        reserv.setReservationDate(new Date());
        reserv.setReservationDateCancel(new Date());
        reserv.setId(1L);
        ReservDTO reservDTO = mapper.toDTO(reserv, ReservDTO.class);
        Assertions.assertAll(
                () -> assertTrue(reserv.getReservationDate().equals(reservDTO.getReservationDate())),
                () -> assertTrue(reserv.getReservationDateCancel().equals(reservDTO.getReservationDateCancel())),
                () -> assertTrue(reserv.getId().equals(reservDTO.getId()))
        );
    }

    @Test
    void toEntity() {
        ReservDTO reservDTO = new ReservDTO();
        reservDTO.setReservationDate(new Date());
        reservDTO.setReservationDateCancel(new Date());
        reservDTO.setId(1L);
        Reserv reserv = mapper.toEntity(reservDTO, Reserv.class);
        Assertions.assertAll(
                () -> assertTrue(reservDTO.getReservationDate().equals(reserv.getReservationDate())),
                () -> assertTrue(reservDTO.getReservationDateCancel().equals(reserv.getReservationDateCancel())),
                () -> assertTrue(reservDTO.getId().equals(reserv.getId()))
        );
    }

}