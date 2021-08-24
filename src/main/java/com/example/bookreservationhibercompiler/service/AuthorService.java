package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.entity.Author;
import java.util.List;

public interface AuthorService extends CommonService<Author, AuthorDTO> {

	public List<AuthorDTO> getByNameLike(String name);

}
