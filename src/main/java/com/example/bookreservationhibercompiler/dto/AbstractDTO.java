package com.example.bookreservationhibercompiler.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class AbstractDTO {

  private String id;

}
