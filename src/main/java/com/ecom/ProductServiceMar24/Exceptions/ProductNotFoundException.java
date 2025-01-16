package com.ecom.ProductServiceMar24.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends Exception{     //now to make an exception class we will extend exceptions. If we extend to runtime expection which is an unchecked exceptions that is not good. Instead use checked Exceptions
    private Long id;
    private String message;
    public ProductNotFoundException(Long id, String message){
        super(message);
        this.id = id;
    }
}
