package com.example.homeinsurance.exception;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;

public class YesNoBooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {

        String value = p.getText();
        String fieldName = p.getCurrentName();

        if (value == null || value.isBlank()) {
            return null;
        }

        if ("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value)) {
            return true;
        }

        if ("false".equalsIgnoreCase(value) || "no".equalsIgnoreCase(value)) {
            return false;
        }

        // 🚨 Explicit error with field name
        throw InvalidFormatException.from(
                p,
                "Invalid Boolean value for field '" + fieldName +
                        "'. Expected true/false or Yes/No",
                value,
                Boolean.class
        );
    }
}
