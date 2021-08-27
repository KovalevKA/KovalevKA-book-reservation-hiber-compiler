package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.example.bookreservationhibercompiler.service.TranslatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@Component
@Path("translators")
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class TranslatorController {

	@Autowired
	private TranslatorService translatorService;

	@GET
	@Path("")
	public List<TranslatorDTO> getAllTranslators() {
		return translatorService.findAll();
	}

	@POST
	@Path("")
	public TranslatorDTO addTranslator(TranslatorDTO translatorDTO) {
		return translatorService.create(translatorDTO);
	}

	@GET
	@Path("find-name-like")
	public List<TranslatorDTO> getTranslatorsByNameLike(@QueryParam("name") String name) {
		return translatorService.getByNameLike(name);
	}

	@PATCH
	@Path("{id}")
	public TranslatorDTO editTranslator(@PathParam("id") Long id, TranslatorDTO translatorDTO) {
		return translatorService.update(id, translatorDTO);
	}

	@DELETE
	@Path("{id}")
	public void deleteTranslator(@PathParam("id") Long id) {
		translatorService.deleteById(id);
	}
}
