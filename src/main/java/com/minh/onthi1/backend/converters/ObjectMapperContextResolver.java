package com.minh.onthi1.backend.converters;//package com.minh.labweb02.converts;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ext.ContextResolver;


public class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {
    final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapperContextResolver() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return objectMapper;
    }
}

