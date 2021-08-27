package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import com.example.bookreservationhibercompiler.entity.Genre;
import com.example.bookreservationhibercompiler.service.GenreService;
import com.example.bookreservationhibercompiler.service.impl.AbstractHibernateService;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl
	extends AbstractHibernateService<Genre, GenreDTO>
	implements GenreService {

	@Autowired(required = false)
	private SessionFactory sessionFactory;

	public GenreServiceImpl() {
		super(Genre.class, GenreDTO.class);
	}

	public List<GenreDTO> getByNameLike(String name) {
		return sessionFactory.getCurrentSession()
			.createQuery("FROM Genre WHERE LOWER(name) LIKE LOWER(:name)")
			.setParameter("name", "%" + name + "%")
			.getResultList()
			;
	}

}
