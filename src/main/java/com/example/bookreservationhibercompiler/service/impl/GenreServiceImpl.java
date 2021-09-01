package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import com.example.bookreservationhibercompiler.entity.Genre;
import com.example.bookreservationhibercompiler.mapper.CommonMapper;
import com.example.bookreservationhibercompiler.service.GenreService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl
        extends CommonServiceImpl<Genre, GenreDTO>
        implements GenreService {

    @Autowired(required = false)
    private SessionFactory sessionFactory;

    @Autowired(required = false)
    private CommonMapper<Genre, GenreDTO> mapper;

    public GenreServiceImpl() {
        super(Genre.class);
    }

    public List<GenreDTO> getByNameLike(String name) {
        return mapper.toDTOs(sessionFactory.getCurrentSession()
                .createQuery("FROM Genre WHERE LOWER(name) LIKE LOWER(:name)")
                .setParameter("name", "%" + name + "%")
                .getResultList()
        );
    }
}
