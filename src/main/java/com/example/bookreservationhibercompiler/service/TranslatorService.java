package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.TranslatorDTO;
import com.example.bookreservationhibercompiler.entity.Translator;
import java.util.List;

public interface TranslatorService extends CommonService<Translator, TranslatorDTO> {

	public List<TranslatorDTO> getByNameLike(String name);

}
