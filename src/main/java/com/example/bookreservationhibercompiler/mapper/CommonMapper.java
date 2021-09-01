package com.example.bookreservationhibercompiler.mapper;

import com.example.bookreservationhibercompiler.dto.AbstractDTO;
import com.example.bookreservationhibercompiler.entity.AbstractEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public interface CommonMapper<E extends AbstractEntity, D extends AbstractDTO> {

    ModelMapper mapper = new ModelMapper();

    D toDTO(E entity);

    E toEntity(D dto);

    default List<D> toDTOs(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<E> toEntities(List<D> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }


}
