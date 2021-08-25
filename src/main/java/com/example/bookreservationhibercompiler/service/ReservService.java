package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCancelReservation;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCheckReservedBooksByBookId;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForMakeReservetion;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ReservService {

	public List<ReservDTO> getReservationClientListById(Long clientId);

	public List<ReservDTO> checkReservedBooksByBookId(RequestParamForCheckReservedBooksByBookId param);

	public List<ReservDTO> make(RequestParamForMakeReservetion param);

	public Integer cancel(RequestParamForCancelReservation param);

}
