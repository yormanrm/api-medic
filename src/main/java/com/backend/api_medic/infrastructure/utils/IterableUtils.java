package com.backend.api_medic.infrastructure.utils;

public class IterableUtils {

    private IterableUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        return iterable == null || !iterable.iterator().hasNext();
    }

}