package com.example.bookreservationhibercompiler.service.impl;

import com.example.bookreservationhibercompiler.dto.AbstractDTO;
import com.example.bookreservationhibercompiler.entity.AbstractEntity;
import com.example.bookreservationhibercompiler.mapper.CommonMapper;
import com.example.bookreservationhibercompiler.repository.CommonRepository;
import com.example.bookreservationhibercompiler.service.CommonService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;

public class CommonServiceImpl<E extends AbstractEntity, D extends AbstractDTO> implements CommonService<D> {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private CommonMapper<E, D> mapper;

    @Autowired
    private CommonRepository<E> repository;

    private Class<E> clazzEntity;
    private Class<D> clazzDTO;

    public CommonServiceImpl(Class<E> clazzEntity, Class<D> clazzDTO) {
        this.clazzEntity = clazzEntity;
        this.clazzDTO = clazzDTO;
    }

    @Override
    public D findOne(long id) {
        return mapper.toDTO(repository.findOne(id, clazzEntity), clazzDTO);
    }

    @Override
    public List<D> findAll() {
        List<E> all = repository.findAll(clazzEntity);
        return mapper.toDTOs(all, clazzDTO);
    }

    @Override
    public D create(D dto) {
        E entity = mapper.toEntity(dto, clazzEntity);
        return mapper.toDTO(repository.create(entity), clazzDTO);
    }

    @Override
    public D update(Long id, D dto) {
        E saveEntity = sessionFactory.getCurrentSession().find(clazzEntity, id);

        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Field saveField;
            try {
                saveField = saveEntity.getClass().getDeclaredField(field.getName());
                saveField.setAccessible(true);
                saveField.set(saveEntity, field.get(dto));
                saveField.setAccessible(false);
            } catch (Exception e) {
                throw new IllegalArgumentException("Field not found");
            }
            field.setAccessible(false);
        }
        return null;
    }

    @Override
    public void delete(D dto) {
        repository.delete(mapper.toEntity(dto, clazzEntity));
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id, clazzEntity);
    }
}
