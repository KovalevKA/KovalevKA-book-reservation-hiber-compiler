package com.example.bookreservationhibercompiler.dto.requestBodyParams;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class RequestParamForCheckReservedBooksByBookId {

    @NotNull(message = "Can't be null")
    private List<Long> listBooksId;
}
