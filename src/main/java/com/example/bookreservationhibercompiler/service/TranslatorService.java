package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.example.bookreservationhibercompiler.entity.Translator;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TranslatorService extends CommonService<Translator, TranslatorDTO> {

	@Transactional(readOnly = true)
	public List<TranslatorDTO> getByNameLike(String name);

}
