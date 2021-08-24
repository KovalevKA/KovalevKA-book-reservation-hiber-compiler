package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservService {

    public List<ReservDTO> getReservationClientListById(Long clientId) {
        return null;
    }
    public List<ReservDTO> checkReservedBooksByBookId(List<Long> listBooksId) {
        return null;
    }

    public List<ReservDTO> make(Long clientId, List<Long> listBooksId, String dateTo){
        return null;
    }

    public Integer cancel (Long clientId, List<Long> listReservId){
        return null;
    }
}
