package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.example.bookreservationhibercompiler.entity.Translator;
import com.example.bookreservationhibercompiler.service.TranslatorService;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslatorServiceImpl
	extends AbstractHibernateService<Translator, TranslatorDTO>
	implements TranslatorService {

	@Autowired
	private SessionFactory sessionFactory;

	public TranslatorServiceImpl() {
		super(Translator.class, TranslatorDTO.class);
	}

	@Override
	public List<TranslatorDTO> getByNameLike(String name) {
		return sessionFactory.getCurrentSession()
			.createQuery("FROM Translator WHERE LOWER(name) LIKE LOWER(:name)")
			.setParameter("name", "%" + name + "%")
			.getResultList()
			;
	}
}
