package com.ecom.ProductServiceMar24.Models.dtos;

import lombok.Getter;
import lombok.Setter;

//dtos=data transfer object are used to receive data from client to controller or send data from controller to client.
//dtos are java objects to map the input or output
//since in the fakestore the output of category class is string but in our product class the datatype is class so to modify this we are using dtos
@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

}
// id:1,
//                    title:'...',
//                    price:'...',
//                    category:'...',
//                    description:'...',
//                    image:'...'