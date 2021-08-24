package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.GenreDTO;
import com.example.bookreservationhibercompiler.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService extends AbstractHibernateService<Genre, GenreDTO> {

    public GenreService() {
        super(Genre.class, GenreDTO.class);
    }

    public List<GenreDTO> getByNameLike(String name) {
        return sessionFactory.getCurrentSession().createQuery("FROM Genre WHERE LOWER(name) LIKE LOWER(:name)")
                .setParameter("name", "%" + name + "%")
                .getResultList()
        ;
    }

}
