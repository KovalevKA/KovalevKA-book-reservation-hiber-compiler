package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenreService extends CommonService<GenreDTO> {

	@Transactional(readOnly = true)
	List<GenreDTO> getByNameLike(String name);

}
