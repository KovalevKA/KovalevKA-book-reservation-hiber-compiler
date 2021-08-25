package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import com.example.bookreservationhibercompiler.entity.Genre;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenreService extends CommonService<Genre, GenreDTO> {

	@Transactional(readOnly = true)
	public List<GenreDTO> getByNameLike(String name);

}
