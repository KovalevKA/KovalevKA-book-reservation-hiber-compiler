package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.entity.Author;
import com.example.bookreservationhibercompiler.repository.CommonRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorService extends CommonService<AuthorDTO>{

	@Transactional(readOnly = true)
	List<AuthorDTO> getByNameLike(String name);

	AuthorDTO findOne(long id);

	List<AuthorDTO> findAll();

	AuthorDTO create(AuthorDTO dto);

	AuthorDTO update(Long id, AuthorDTO dto);

	void delete(AuthorDTO authorDTO);

	void deleteById(long id);

}
