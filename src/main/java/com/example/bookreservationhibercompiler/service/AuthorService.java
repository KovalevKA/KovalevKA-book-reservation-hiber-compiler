package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.AuthorDTO;
import com.example.bookreservationhibercompiler.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService extends AbstractHibernateService<Author, AuthorDTO> {

    public AuthorService() {
        super(Author.class, AuthorDTO.class);
    }

    public List<AuthorDTO> getByNameLike(String name){
        return sessionFactory.getCurrentSession().createQuery("FROM Author WHERE LOWER(name) LIKE LOWER(:name)")
                .setParameter("name", "%" + name + "%")
                .getResultList()
                ;
    }
}
