package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.example.bookreservationhibercompiler.entity.Translator;
import com.example.bookreservationhibercompiler.mapper.CommonMapper;
import com.example.bookreservationhibercompiler.service.TranslatorService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorServiceImpl
        extends CommonServiceImpl<Translator, TranslatorDTO>
        implements TranslatorService {

    @Autowired(required = false)
    private SessionFactory sessionFactory;

    @Autowired(required = false)
    private CommonMapper<Translator, TranslatorDTO> mapper;

    public TranslatorServiceImpl() {
        super(Translator.class, TranslatorDTO.class);
    }

    @Override
    public List<TranslatorDTO> getByNameLike(String name) {
        return mapper.toDTOs(sessionFactory.getCurrentSession()
                .createQuery("FROM Translator WHERE LOWER(name) LIKE LOWER(:name)")
                .setParameter("name", "%" + name + "%")
                .getResultList(), TranslatorDTO.class);
    }
}
