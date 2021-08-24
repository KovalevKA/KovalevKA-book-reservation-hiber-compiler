package com.example.bookreservationhibercompiler.controller;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import com.example.bookreservationhibercompiler.service.GenreService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("genres")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@GetMapping
	public List<GenreDTO> getAllGenres() {
		return genreService.findAll();
	}

	@PostMapping
	public GenreDTO addGenre(@RequestBody GenreDTO genreDTO) {
		return (GenreDTO) genreService.create(genreDTO);
	}

	@GetMapping("find-name-like")
	public List<GenreDTO> getGenresByNameLike(
		@RequestParam(name = "name") String name) {
		return genreService.getByNameLike(name);
	}

	@PatchMapping("{id}")
	public GenreDTO editGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
		return (GenreDTO) genreService.update(id, genreDTO);
	}

	@DeleteMapping("{id}")
	public void deleteGenre(@PathVariable Long id) {
		genreService.deleteById(id);
	}
}
