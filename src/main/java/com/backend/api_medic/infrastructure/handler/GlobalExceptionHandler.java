package com.backend.api_medic.infrastructure.handler;

import com.backend.api_medic.infrastructure.dto.ApiResponseDTO;
import com.backend.api_medic.infrastructure.exception.CustomException;
import com.backend.api_medic.infrastructure.exception.PatientAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de cualquier otra excepcion inesperada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleGeneralExceptions(Exception ex) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                500, true, "Unexpected error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejo de exceptions personalizadas
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleCustomExceptions(CustomException ex) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                -1, true, "Unexpected error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    // Manejo de errores de validación
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                400, true, "Validation error", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejo de pacientes duplicados
    @ExceptionHandler(PatientAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handlePatientAlreadyExistsExceptions(PatientAlreadyExistsException ex) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                409, true, "Unexpected error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}