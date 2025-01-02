package com.dlt.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class ObjectMapperProvider {

    @Produces
    ObjectMapper configureObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // Register the JavaTimeModule to handle Java 8 date/time types
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(module);
        // Disable SerializationFeature.WRITE_DATES_AS_TIMESTAMPS to ensure dates are in ISO format
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }
}
