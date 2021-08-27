package com.example.bookreservationhibercompiler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "client_id"))
@Table(name = "client")
public class Client extends AbstractEntity {

  @JsonIgnore
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
  private final List<Reserv> reservList = new ArrayList<>();
  @Column(name = "name")
  private String name;

  public void addReserv(Reserv reserv) {
    this.reservList.add(reserv);
  }
}
