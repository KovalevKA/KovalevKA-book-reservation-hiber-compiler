package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.example.bookreservationhibercompiler.entity.Translator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorService extends AbstractHibernateService<Translator, TranslatorDTO>{
    public TranslatorService() {
        super(Translator.class, TranslatorDTO.class);
    }

    public List<TranslatorDTO> getByNameLike(String name){
        return sessionFactory.getCurrentSession().createQuery("FROM Translator WHER LOWER(name) LIKE LOWER(:name)")
                .setParameter("name", "%" + name + "%")
                .getResultList()
                ;
    }
}
