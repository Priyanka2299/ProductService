package com.ecom.ProductServiceMar24.Models;

import lombok.Getter;       //lombok is used to import getter & setter methods
import lombok.Setter;

@Getter
@Setter             //if we use @data inplace of @getter @setter. @data serves the purpose for both

public class Product {
    private Long Id;
    private String Title;
    private String Description;
    private Category Category;
    private double Price;
    private String Image;
}
