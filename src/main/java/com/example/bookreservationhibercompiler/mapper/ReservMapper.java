package com.example.bookreservationhibercompiler.mapper;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import com.example.bookreservationhibercompiler.entity.Reserv;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class ReservMapper implements CommonMapper<Reserv, ReservDTO>{
    @Override
    public ReservDTO toDTO(Reserv reserv) {
        return mapper.map(reserv, (Type) ReservDTO.class);
    }

    @Override
    public Reserv toEntity(ReservDTO reservDTO) {
        return mapper.map(reservDTO, Reserv.class);
    }
}
