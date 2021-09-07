package com.example.bookreservationhibercompiler.service;

import com.example.bookreservationhibercompiler.dto.AbstractDTO;

import java.util.List;

public interface CommonService<D extends AbstractDTO> {

    public D findOne(long id);

    public List<D> findAll();

    public D create(D dto);

    public D update(Long id, D dto);

    public void delete(D dto);

    public void deleteById(long id);
}
