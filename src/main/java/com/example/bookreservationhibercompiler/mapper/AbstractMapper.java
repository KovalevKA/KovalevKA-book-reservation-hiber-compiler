package com.example.bookreservationhibercompiler.mapper;

import com.example.bookreservationhibercompiler.dto.AbstractDTO;
import com.example.bookreservationhibercompiler.entity.AbstractEntity;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

public class AbstractMapper<Entity extends AbstractEntity, DTO extends AbstractDTO> {

    ModelMapper mapper = new ModelMapper();

    private Class<Entity> entityClass;
    private Class<DTO> dtoClass;

    public AbstractMapper(Class<Entity> entityClass, Class<DTO> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public <Entity> Entity toEntity(DTO dto) {
        return mapper.map(dto, (Type) entityClass);
    }

    public <DTO> DTO toDTO(Entity entity) {
        return mapper.map(entity, (Type) dtoClass);
    }

    public <Entity> Collection<Entity> toEntities(Collection<DTO> dtos) {
        return (Collection<Entity>) dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public <DTO> Collection<DTO> toDTOs(Collection<Entity> entities) {
        return (Collection<DTO>) entities.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
