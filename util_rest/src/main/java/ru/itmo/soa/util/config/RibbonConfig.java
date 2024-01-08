package ru.itmo.soa.util.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.apache.hc.core5.http.HttpHeaders.TIMEOUT;

@Configuration
public class RibbonConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate( MappingJackson2XmlHttpMessageConverter xmlConverter) {
        var template =  new RestTemplate();

        template.getMessageConverters().add(xmlConverter);

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        template.setRequestFactory(requestFactory);

        return template;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MappingJackson2XmlHttpMessageConverter xmlConverter(@Qualifier("dateObjectMapper") ObjectMapper mapper) {

        return new MappingJackson2XmlHttpMessageConverter(mapper);
    }
    @Bean
    @Qualifier("dateObjectMapper")
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        var dateTimeModule = new SimpleModule();
//        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        dateTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
//
//
//        ObjectMapper objectMapper = builder.createXmlMapper(true).build();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.registerModule(dateTimeModule);
//        return objectMapper;
        return new XmlMapper().registerModule(new JavaTimeModule());
    }
}