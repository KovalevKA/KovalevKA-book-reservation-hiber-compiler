package com.example.bookreservationhibercompiler.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface CommonService<Entity, DTO> {

	public DTO findOne(long id);

	public List findAll();

	public DTO create(DTO dto);

	public DTO update(Long id, DTO dto);

	public void delete(Entity entity);

	public void deleteById(long id);

	}
