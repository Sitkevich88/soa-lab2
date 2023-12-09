package ru.itmo.soa.util.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(@Autowired ObjectMapper mapper) {
        var template =  new RestTemplate();
        var messageConverter = new MappingJackson2XmlHttpMessageConverter();
        
        messageConverter.setObjectMapper(mapper);
        template.getMessageConverters().add(messageConverter);
        
        return template;
    }
}