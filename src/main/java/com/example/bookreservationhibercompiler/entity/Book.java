package com.example.bookreservationhibercompiler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Indexed(index = "book")
@Entity
@Table(name = "book")
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book extends AbstractEntity implements Serializable {

  public static final String INDEX = "books";

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
  private final List<Reserv> reservList = new ArrayList<>();

  @IndexedEmbedded
  @ManyToMany(mappedBy = "bookList",
          fetch = FetchType.EAGER,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Author> authorList = new HashSet<>();

  @IndexedEmbedded
  @ManyToMany(mappedBy = "bookList",
          fetch = FetchType.EAGER,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Genre> genreList = new HashSet<>();

  @IndexedEmbedded
  @ManyToMany(mappedBy = "bookList",
          fetch = FetchType.EAGER,
          cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private final Set<Translator> translatorList = new HashSet<>();

  @FullTextField
  @Column(name = "name")
  private String name;

  @NaturalId
  @DocumentId
  private String isbn;

  @FullTextField
  @Column(name = "publishing_house")
  private String publishHouse;

  @GenericField
  @Column(name = "publishing_year")
  private int publishYear;

  @Column(name = "description")
  private String description;

  public void addAuthor(Author author) {
    this.authorList.add(author);
  }

  public void addGenre(Genre genre) {
    this.genreList.add(genre);
  }

  public void addTranslator(Translator translator) {
    this.translatorList.add(translator);
  }

  public void addReserv(Reserv reserv) {
    this.reservList.add(reserv);
  }

  public void removeAuthor(Author author) {
    this.authorList.remove(author);
  }

  public void removeGenre(Genre genre) {
    this.genreList.remove(genre);
  }

  public void removeTranslator(Translator translator) {
    this.translatorList.remove(translator);
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
