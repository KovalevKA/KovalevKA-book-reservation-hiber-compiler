package com.example.bookreservationhibercompiler.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "reserv")
@AttributeOverride(name = "id", column = @Column(name = "reserv_id"))
public class Reserv extends AbstractEntity {

  @ManyToOne
  @JoinColumn(name = "client_id")
  private Client client = new Client();
  @ManyToOne()
  @JoinColumn(name = "book_id")
  private Book book = new Book();
  @Column(name = "reservation_date")
  private Date reservationDate = new Date();
  @Column(name = "reservation_date_cancel")
  private Date reservationDateCancel = new Date();

  public Reserv(Client client, Book book, Date reservationDateCancel) {
    this.book = book;
    book.addReserv(this);
    this.client = client;
    client.addReserv(this);
    this.reservationDate = new Date();
    this.reservationDateCancel = reservationDateCancel;
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
