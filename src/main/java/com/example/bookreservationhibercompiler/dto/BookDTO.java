package com.example.bookreservationhibercompiler.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookDTO extends AbstractDTO {

  private String isbn;
  private String name;
  private String publishHouse;
  private int publishYear;
  private String description;
  private Set<AuthorDTO> authorList = new HashSet<>();
  private Set<GenreDTO> genreList = new HashSet<>();
  private Set<TranslatorDTO> translatorList = new HashSet<>();
}
