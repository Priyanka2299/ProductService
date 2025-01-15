package com.ecom.ProductServiceMar24.Services;

import com.ecom.ProductServiceMar24.Models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();

}
