package com.example.bookreservationhibercompiler.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface CommonMapper<Entity, DTO> {

    ModelMapper mapper = new ModelMapper();

    @PostConstruct
    default void modelMapperSetting() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    default <DTO> DTO toDTO(Entity entity) {
        Class<DTO> dtoClass =
                (Class<DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        return mapper.map(entity, (Type) dtoClass);
    }

    default <Entity> Entity toEntity(DTO dto) {
        Class<Entity> entityClass =
                (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return mapper.map(dto, (Type) entityClass);
    }

    default <DTO> List<DTO> toDTOs(Collection<Entity> entities) {
        return (List<DTO>) entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default <Entity> Collection<Entity> toEntities(Collection<DTO> dtos) {
        return (Collection<Entity>) dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }


}
