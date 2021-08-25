package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCancelReservation;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCheckReservedBooksByBookId;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForMakeReservetion;
import com.example.bookreservationhibercompiler.service.ReservService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservServiceImpl implements ReservService {

	public List<ReservDTO> getReservationClientListById(Long clientId) {
		return null;
	}

	public List<ReservDTO> checkReservedBooksByBookId(RequestParamForCheckReservedBooksByBookId param) {
		return null;
	}

	public List<ReservDTO> make(RequestParamForMakeReservetion param) {
		return null;
	}

	public Integer cancel(RequestParamForCancelReservation param) {
		return null;
	}
}
