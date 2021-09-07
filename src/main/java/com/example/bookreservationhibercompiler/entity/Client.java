package com.example.bookreservationhibercompiler.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
