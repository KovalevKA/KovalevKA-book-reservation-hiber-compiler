package com.example.bookreservationhibercompiler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "translator_id"))
@Table(name = "translator")
public class Translator extends AbstractEntity {

  @JsonIgnore
  @ManyToMany(fetch = FetchType.LAZY, targetEntity = Book.class, cascade = {CascadeType.PERSIST,
          CascadeType.MERGE})
  @JoinTable(name = "translator_book",
          joinColumns = {@JoinColumn(name = "translator_id", referencedColumnName = "translator_id")},
          inverseJoinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "book_id")})
  private final List<Book> bookList = new ArrayList<>();

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
    if (this == o) {
      return true;
    }
    if (!(o instanceof Translator)) {
      return false;
    }
    Translator that = (Translator) o;
    return name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
