package com.example.bookreservationhibercompiler.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface CommonMapper<E, D> {

    ModelMapper mapper = new ModelMapper();

    @PostConstruct
    default void modelMapperSetting() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    default D toDTO(E entity) {
        Class<D> dtoClass =
                (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return mapper.map(entity, (Type) dtoClass);
    }

    default E toEntity(D dto) {
        Class<E> entityClass =
                (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return mapper.map(dto, (Type) entityClass);
    }

    default List<D> toDTOs(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<E> toEntities(List<D> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }


}
