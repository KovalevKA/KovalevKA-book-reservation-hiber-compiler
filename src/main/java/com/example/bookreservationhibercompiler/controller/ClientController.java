package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.ClientDTO;
import com.example.bookreservationhibercompiler.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("clients")
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

	@Autowired
	private ClientService clientService;

	@GET
	public ClientDTO getClientByParams(@QueryParam("name") String name) {
		return null;
	}

	@POST
	public ClientDTO addClient(ClientDTO clientDTO) {
		return clientService.create(clientDTO);
	}

	@PATCH
	@Path("{id}")
	public ClientDTO editClient(@PathParam("id") Long id, ClientDTO clientDTO) {
		return clientService.update(id, clientDTO);
	}

	@DELETE
	@Path("{id}")
	public void deleteClient(@PathParam("id") Long id) {
		clientService.deleteById(id);
	}
}
