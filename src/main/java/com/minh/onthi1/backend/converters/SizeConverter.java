package com.minh.onthi1.backend.converters;

import com.minh.onthi1.backend.enums.Color;
import com.minh.onthi1.backend.enums.Size;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class SizeConverter implements AttributeConverter<Size, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Size attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.ordinal();
    }

    @Override
    public Size convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(Size.values())
                .filter(c -> c.ordinal() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
