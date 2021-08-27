package com.example.bookreservationhibercompiler.dto.requestBodyParams;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class RequestParamForCancelReservation {

    @NotNull
    private Long clientId;
    @NotNull
    private List<Long> listReservId;

}
