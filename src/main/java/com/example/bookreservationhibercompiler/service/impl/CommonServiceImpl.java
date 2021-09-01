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

    @Autowired(required = false)
    private CommonMapper<E, D> mapper;

    @Autowired
    private CommonRepository<E> repository;

    private Class<E> clazzEntity;

    public CommonServiceImpl(Class<E> clazzEntity) {
        this.clazzEntity = clazzEntity;
    }

    @Override
    public D findOne(long id) {
        return mapper.toDTO(repository.findOne(id));
    }

    @Override
    public List<D> findAll() {
        List<E> all = repository.findAll();
        return null;
    }

    @Override
    public D create(D dto) {
        E entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.create(entity));
    }

    @Override
    public D update(Long id, D dto) {
        E saveEntity = sessionFactory.getCurrentSession().get(clazzEntity, id);

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
        repository.delete(mapper.toEntity(dto));
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
