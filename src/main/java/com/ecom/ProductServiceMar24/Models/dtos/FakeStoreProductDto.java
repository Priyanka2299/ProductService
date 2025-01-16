package com.ecom.ProductServiceMar24.Models.dtos;

import lombok.Getter;
import lombok.Setter;

//dtos=data transfer object are used to receive data from client to controller or send data from controller to client.
//dtos are java objects to map the input or output
//since in the fakestore the output of category class is string but in our product class the datatype is class so to modify this we are using dtos
@Getter
@Setter
public class FakeStoreProductDto {
    private Long Id;
    private String Title;
    private double Price;
    private String Category;
    private String Description;
    private String Image;


//    public FakeStoreProductDto {
//        Title
//        String title1 = Title;
//        Long Id, String Title, double Price, String Category, String Description, String Image){
//        this.Id = Id;
//        this.Title = Title;
//        this.Price = Price;
//        this.Category = Category;
//        this.Description = Description;
//        this.Image = Image;
//
//
//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long Id) {
//        this.Id = Id;
//    }
//    public String getTitle() {
//        return Title;
//    }
}