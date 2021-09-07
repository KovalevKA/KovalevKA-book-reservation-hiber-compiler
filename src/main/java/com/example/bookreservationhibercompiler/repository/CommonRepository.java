package com.example.bookreservationhibercompiler.repository;

import java.util.List;

public interface CommonRepository<E> {

	E findOne(long id, Class<E> type);

	List<E> findAll(Class<E> type);

	E create(E entity);

	E update(E entity);

	void delete(E entity);

	public void deleteById(long id, Class<E> type);

}
