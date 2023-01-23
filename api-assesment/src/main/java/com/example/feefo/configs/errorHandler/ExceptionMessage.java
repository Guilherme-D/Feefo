package com.example.feefo.configs.errorHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {

    private String message;
    private String error;
    private String path;
    private Integer statusCode;
    private Exception ex;


}
