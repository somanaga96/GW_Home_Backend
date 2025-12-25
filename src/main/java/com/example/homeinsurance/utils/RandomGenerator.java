package com.example.homeinsurance.utils;

import java.util.UUID;

public class RandomGenerator {
    public static <T extends Enum<T>> T parseEnum(String value, Class<T> enumType, String fieldName) {
        try {
            return Enum.valueOf(enumType, value);
        } catch (Exception e) {
            throw new RuntimeException("Invalid " + fieldName + ": " + value);
        }
    }

    public static String generatePolicyNumber() {
        return "POL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
