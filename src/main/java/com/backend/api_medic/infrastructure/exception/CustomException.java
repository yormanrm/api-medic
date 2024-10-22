package com.backend.api_medic.infrastructure.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}