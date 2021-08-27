package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.ReservDTO;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCancelReservation;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForCheckReservedBooksByBookId;
import com.example.bookreservationhibercompiler.dto.requestBodyParams.RequestParamForMakeReservetion;
import com.example.bookreservationhibercompiler.service.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@Component
@Path("reservation")
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class ReservController {

	@Autowired
	private ReservService reservService;

	@GET()
	public List<ReservDTO> getListReservByClientId(
			@QueryParam("clientId") Long clientId) {
		return reservService.getReservationClientListById(clientId);
	}

	@POST
	@Path("check")
	public List<ReservDTO> checkReservedBooksByBookId(
			RequestParamForCheckReservedBooksByBookId listBooksId) {
		return reservService.checkReservedBooksByBookId(listBooksId);
	}

	@POST
	@Path("make")
	public List<ReservDTO> makeReservation(
			RequestParamForMakeReservetion param) {
		return reservService.make(param);
	}

	@POST
	@Path("cancel")
	public Integer cancelReservation(RequestParamForCancelReservation param) {
		return reservService.cancel(param);
	}
}
