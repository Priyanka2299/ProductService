package com.ecom.ProductServiceMar24.Configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration  //this is a configuration class. Through this you are giving instructions to run this class at the time of initialization. This means that spring will be ready to go through this class and go through all the return type of the methods- here in this case it will create an object/bean of rest template
public class RestTemplateConfig {
    @Bean                           //If I need single object and not multiple objects -> go with bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
