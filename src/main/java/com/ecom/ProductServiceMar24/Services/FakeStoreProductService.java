package com.ecom.ProductServiceMar24.Services;

import com.ecom.ProductServiceMar24.Exceptions.ProductNotFoundException;
import com.ecom.ProductServiceMar24.Models.Category;
import com.ecom.ProductServiceMar24.Models.Product;
import com.ecom.ProductServiceMar24.Models.dtos.FakeStoreProductDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service // you can also use @component or @repository   which is generic
@Getter
@Setter
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
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Call 3rd party APi or FakeStore API here to get the Product with given id, we need some component which is resttemplate. This template comes with Spring web dependency

        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products" + id, FakeStoreProductDto.class); // here fakestoreproductdto is the return type as we will get the response from https://fakestoreapi.com/products in JSON format
        //Convert FakeStore DTO into Product object
        if (fakeStoreProductDto == null) {
            throw new ProductNotFoundException(id, "Product with id" + id + "Not found");
        //    return null;
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }
    @Override
    public List<Product> getAllProducts() { //here we aew using array instead of list coz of typ erase property
        FakeStoreProductDto [] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        List<Product> response = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            response.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return response;
    }

    @Override
    public Product replaceProduct(long id, Product product) {   //whatever product object we are getting in the input, i will not send as it is to the fakestore. i will convert that product object into fakestore object
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto(); //since this is the class specific to fakestore, i can create an object of fakestore and send in request
        fakeStoreProductDto.setTitle(product.getTitle()); //set the parameter specific to fakestore
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);// in the place of request instead of product i have written dto
        HttpMessageConverterExtractor <FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(response);
    }


}
