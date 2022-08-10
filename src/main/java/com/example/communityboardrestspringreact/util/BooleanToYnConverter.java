package com.example.communityboardrestspringreact.util;

import javax.persistence.AttributeConverter;

public class BooleanToYnConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        return "Y".equalsIgnoreCase(value);
    }

}
