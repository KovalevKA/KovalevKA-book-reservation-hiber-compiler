package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.entity.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorService extends CommonService<Author, AuthorDTO> {

	@Transactional(readOnly = true)
	public List<AuthorDTO> getByNameLike(String name);

}
