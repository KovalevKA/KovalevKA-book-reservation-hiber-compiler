package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TranslatorService extends CommonService<TranslatorDTO> {

	@Transactional(readOnly = true)
	List<TranslatorDTO> getByNameLike(String name);

}
