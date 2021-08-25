package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import com.example.bookreservationhibercompiler.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@Component
@Path("genres")
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GET
	@Path("")
	public List<GenreDTO> getAllGenres() {
		return genreService.findAll();
	}

	@POST
	@Path("")
	public GenreDTO addGenre(GenreDTO genreDTO) {
		return (GenreDTO) genreService.create(genreDTO);
	}

	@GET
	@Path("find-name-like")
	public List<GenreDTO> getGenresByNameLike(
			@QueryParam("name") String name) {
		return genreService.getByNameLike(name);
	}

	@PATCH
	@Path("{id}")
	public GenreDTO editGenre(@PathParam("id") Long id, GenreDTO genreDTO) {
		return (GenreDTO) genreService.update(id, genreDTO);
	}

	@DELETE
	@Path("{id}")
	public void deleteGenre(@PathParam("id") Long id) {
		genreService.deleteById(id);
	}
}
