package com.example.feefo.models.RequestEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevenshteinRequest {

    @NotNull(message = "Input field can not be null")
    @NotEmpty(message = "Input field can not be empty")
    private String input;
    @NotNull(message = "Input field can not be null")
    @NotEmpty(message = "Input field can not be empty")
    private String title;

}

