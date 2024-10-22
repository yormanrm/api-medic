package com.backend.api_medic.infrastructure.handler;

import com.backend.api_medic.infrastructure.dto.ApiResponseDTO;
import com.backend.api_medic.infrastructure.exception.CustomException;
import com.backend.api_medic.infrastructure.exception.PatientAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de cualquier otra excepcion inesperada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleGeneralException(Exception ex) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                500, true, "Unexpected error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejo de exceptions personalizadas
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handleCustomException(CustomException ex) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                -1, true, "Unexpected error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    // Manejo de pacientes duplicados
    @ExceptionHandler(PatientAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDTO<Object>> handlePatientAlreadyExistsException(PatientAlreadyExistsException ex) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                409, true, "Unexpected error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}