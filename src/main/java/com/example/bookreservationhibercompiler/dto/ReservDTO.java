package com.example.bookreservationhibercompiler.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservDTO extends AbstractDTO {

  private Date reservationDate;
  private Date reservationDateCancel;
  private BookDTO book;
}
