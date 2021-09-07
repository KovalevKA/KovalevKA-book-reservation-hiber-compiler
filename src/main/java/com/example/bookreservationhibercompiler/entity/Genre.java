package com.example.bookreservationhibercompiler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "genre_id"))
@Table(name = "genre")
public class Genre extends AbstractEntity {

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY, targetEntity = Book.class, cascade = {CascadeType.PERSIST,
          CascadeType.MERGE})
  @JoinTable(name = "genre_book",
          joinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "genre_id")},
          inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
  private final List<Book> bookList = new ArrayList<>();

  @FullTextField
  @Column(name = "name")
  private String name;

  public void addBook(Book book) {
    this.bookList.add(book);
  }

  public void removeBook(Book book) {
    this.bookList.remove(book);
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
