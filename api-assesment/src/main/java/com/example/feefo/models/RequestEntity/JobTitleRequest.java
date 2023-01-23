package com.example.feefo.models.RequestEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobTitleRequest {

    @NotNull(message = "Title field can not be null")
    @NotEmpty(message = "Title field can not be empty")
    private String title;
    @NotNull(message = "New Title field can not be null")
    @NotEmpty(message = "New Title field can not be empty")
    private String newTitle;

}

