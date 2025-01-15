package com.ecom.ProductServiceMar24.Services;

import com.ecom.ProductServiceMar24.Models.Category;
import com.ecom.ProductServiceMar24.Models.Product;
import com.ecom.ProductServiceMar24.Models.dtos.FakeStoreProductDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service // you can also use @component or @repository   which is generic
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate; //RestTemplate restTemplate = new RestTemplate(); //If we have 20-30 APIs in your project and you are creating rest object/bean in every api..is it a good practice? no // so we will create a centralized bean & store it in a container of spring and reuse it later // so we will an instruction to your spring to store the bean in your container and whenever I need it, I'll use it
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        Category category = new Category();
        category.setDesc(dto.getCategory());
        product.setCategory(category);
        product.setDescription(dto.getDescription());
        product.setImage(dto.getImage());


        return product;

    }
    @Override
    public Product getProductById(Long id) {
        //Call 3rd party APi or FakeStore API here to get the Product with given id, we need some component which is resttemplate. This template comes with Spring web dependency

        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products" + id, FakeStoreProductDto.class); // here fakestoreproductdto is the return type as we will get the response from https://fakestoreapi.com/products in JSON format
        //Convert FakeStore DTO into Product object
        if (fakeStoreProductDto == null) {
            return null;
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }
    @Override
    public List<Product> getAllProducts() {

        return null;
    }



}
