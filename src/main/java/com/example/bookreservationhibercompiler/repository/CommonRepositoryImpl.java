package com.example.bookreservationhibercompiler.repository;

import com.example.bookreservationhibercompiler.entity.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public class CommonRepositoryImpl<E extends AbstractEntity>
		implements CommonRepository<E> {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public E findOne(long id) {
		Class<E> eType =
				(Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return getCurrentSession().get(eType, id);
	}

	@Transactional(readOnly = true)
	public List<E> findAll() {
		Class<E> eType =
				(Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return (List<E>) getCurrentSession().createQuery("from " + eType.getName()).list();
	}

	@Transactional
	public E create(E entity) {
		getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	@Transactional
	public E update(E entity) {
		return (E) getCurrentSession().merge(entity);
	}

	@Transactional
	public void delete(E entity) {
		getCurrentSession().delete(entity);
	}

	@Transactional
	public void deleteById(long id) {
		Class<E> eType =
				(Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		E entity = sessionFactory.getCurrentSession().get(eType, id);
		delete(entity);
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
