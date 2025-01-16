package com.ecom.ProductServiceMar24.Models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto { // in case if you want to pass more data to the client you will use Exception Dto
    private String message;
    private String resolution;
}

