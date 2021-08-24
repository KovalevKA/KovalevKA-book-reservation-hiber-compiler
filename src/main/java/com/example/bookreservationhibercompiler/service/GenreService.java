package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import com.example.bookreservationhibercompiler.entity.Genre;
import java.util.List;

public interface GenreService extends CommonService<Genre, GenreDTO> {

	public List<GenreDTO> getByNameLike(String name);

}
