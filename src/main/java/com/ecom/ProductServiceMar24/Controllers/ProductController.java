package com.ecom.ProductServiceMar24.Controllers;

import com.ecom.ProductServiceMar24.Models.Product;
import com.ecom.ProductServiceMar24.Services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);

    }      //getProductById is an API
    //localhost:8080/products/1         // this will be the complete endpoint to return all the products
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }
    //createProduct
    //deleteProduct
    //updateProduct -> update means partial update or patch request
}
