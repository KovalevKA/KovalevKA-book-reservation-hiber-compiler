package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import java.util.List;

public interface ReservService {

	public List<ReservDTO> getReservationClientListById(Long clientId);

	public List<ReservDTO> checkReservedBooksByBookId(List<Long> listBooksId);

	public List<ReservDTO> make(Long clientId, List<Long> listBooksId, String dateTo);

	public Integer cancel(Long clientId, List<Long> listReservId);

}
