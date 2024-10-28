package com.backend.api_medic.infrastructure.exception;

public class EmptyIterableException extends RuntimeException {
    public EmptyIterableException(String message) {
        super(message);
    }
}