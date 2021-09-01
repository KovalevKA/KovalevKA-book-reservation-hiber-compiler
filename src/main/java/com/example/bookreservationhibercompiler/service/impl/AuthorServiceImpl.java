package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.entity.Author;
import com.example.bookreservationhibercompiler.mapper.CommonMapper;
import com.example.bookreservationhibercompiler.service.AuthorService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl
		extends CommonServiceImpl<Author, AuthorDTO>
		implements AuthorService {

	@Autowired(required = false)
	private SessionFactory sessionFactory;

	@Autowired(required = false)
	private CommonMapper<Author, AuthorDTO> mapper;

	public AuthorServiceImpl() {
		super(Author.class);
	}

	@Override
	public List<AuthorDTO> getByNameLike(String name) {
		return mapper.toDTOs(sessionFactory.getCurrentSession()
				.createQuery("FROM Author WHERE LOWER(name) LIKE LOWER(:name)")
				.setParameter("name", "%" + name + "%")
				.getResultList()
		);
	}
}
