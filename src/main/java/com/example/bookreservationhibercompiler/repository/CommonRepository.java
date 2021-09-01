package com.example.bookreservationhibercompiler.repository;

import java.util.List;

public interface CommonRepository<Entity> {

	public Entity findOne(long id);

	public List<Entity> findAll();

	public Entity create(Entity dto);

	public Entity update(Entity dto);

	public void delete(Entity Entityy);

	public void deleteById(long id);

}
