package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.entity.Author;
import com.example.bookreservationhibercompiler.service.AuthorService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl
	extends AbstractHibernateService<Author, AuthorDTO>
	implements AuthorService {

	@Autowired(required = false)
	private SessionFactory sessionFactory;

	public AuthorServiceImpl() {
		super(Author.class, AuthorDTO.class);
	}

	@Override
	public List<AuthorDTO> getByNameLike(String name) {
		return toDTOs(sessionFactory.getCurrentSession()
			.createQuery("FROM Author WHERE LOWER(name) LIKE LOWER(:name)")
			.setParameter("name", "%" + name + "%")
			.getResultList()
		);
	}
}
