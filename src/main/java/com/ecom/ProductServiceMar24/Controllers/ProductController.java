package com.ecom.ProductServiceMar24.Controllers;

import com.ecom.ProductServiceMar24.Exceptions.ProductNotFoundException;
import com.ecom.ProductServiceMar24.Models.Product;
import com.ecom.ProductServiceMar24.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//localhost:8080/products
@RestController             //This controller is going to host REST HTTP APIs
@RequestMapping("/products")//This request mapping defines that if user requests /products type of request it will go to product controller
public class ProductController {
    private ProductService productService;
    ProductController(ProductService productService) {
        this.productService = productService;
    }
    //localhost:8080/products/1                 //this id will come in the address. so "1" here is an id
    @GetMapping("/{id}")      // this is a get request but we cannot write "/get/id" in the endpoint of get request coz it will not be rest compliant
                                //here id is placed in curly bracket {id} the defines the dynamic variable if user want to give dynamic variable in the address. WIthout curly brackets it will become a part of the address
//    public Product getProductById(@PathVariable("id") Long id) {
//        return productService.getProductById(id);
//    }
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) throws ProductNotFoundException {      //with the help of ResponseEntity we can set http status code, you will body amd status and headers(this is used for Authoritization )
        Product product = productService.getProductById(id);
        ResponseEntity<Product> responseEntity;
//        if (product == null) {
//            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            return responseEntity;
//        }
        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity; //create a new object of ResponseEntity and 'product' will be set as a Response body
//        // this is useful if there are certain products in which only admin can access. Lets suppose if you have product with id =10 and you want only admin can access you can set that this particular user_id = having admin acces can only this particular product and sharing response as OK else authorization code which is 403
    }
//        ResponseEntity<Product> responseEntity = null; // I will create a responseEntity object
//        try {
//            Product product = productService.getProductById(id); //if in the try block there is no exception, i will send the status code as ok
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        } catch(ArithmeticException e){ //if there is an exception, I will throw internal server error in responseEntity
//            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//        //throw new exception
//    }

         //getProductById is an API
    //localhost:8080/products/1         // this will be the complete endpoint to return all the products
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    //createProduct
    //deleteProduct
    //updateProduct -> update means partial update or patch request
    //replaceProduct -> PUT request
    @PutMapping("/{id")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.replaceProduct(id, product);
    }
}
