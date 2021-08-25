package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.AbstractDTO;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import java.util.List;

public interface CommonElasticSearchService
        <DTO extends AbstractDTO> extends ApplicationListener<ApplicationReadyEvent> {

    List<DTO> search(String keyWords) throws Exception;

}
