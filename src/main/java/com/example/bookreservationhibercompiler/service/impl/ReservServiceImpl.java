package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import com.example.bookreservationhibercompiler.service.ReservService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReservServiceImpl implements ReservService {

	public List<ReservDTO> getReservationClientListById(Long clientId) {
		return null;
	}

	public List<ReservDTO> checkReservedBooksByBookId(List<Long> listBooksId) {
		return null;
	}

	public List<ReservDTO> make(Long clientId, List<Long> listBooksId, String dateTo) {
		return null;
	}

	public Integer cancel(Long clientId, List<Long> listReservId) {
		return null;
	}
}
