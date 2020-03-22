package com.chemistry.elements.domain;

import java.util.stream.Stream;

public enum Type {
    METAL,
    SEMI_METAL,
    NON_METAL,
    INERT_GAS;

    public static boolean isPresent(String type) {
        return Stream.of(Type.values())
                .anyMatch(i -> i.name().equals(type.toUpperCase()));
    }
}
