package com.ecom.ProductServiceMar24.ExceptionHandler;

import com.ecom.ProductServiceMar24.Exceptions.ProductNotFoundException;
import com.ecom.ProductServiceMar24.Models.dtos.ExceptionDto;
import com.ecom.ProductServiceMar24.Models.dtos.ProductNotFoundExceptionDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmetricException(){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong"); // with this along with the status code we are also passing a proper message and resolution
        exceptionDto.setResolution("Nothing can be done");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException exception){
        ProductNotFoundExceptionDto dto = new ProductNotFoundExceptionDto();
        dto.setMessage("Product with the given " +  exception.getId() + " does not found");

        return  new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
