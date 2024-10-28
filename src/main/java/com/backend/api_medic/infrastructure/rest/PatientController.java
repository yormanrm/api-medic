package com.backend.api_medic.infrastructure.rest;

import com.backend.api_medic.application.PatientService;
import com.backend.api_medic.domain.model.Patient;
import com.backend.api_medic.infrastructure.dto.ApiResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patient")
@CrossOrigin(origins = "http://localhost:4200/")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Object>> register(@Valid @RequestBody Patient patient) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(201, false, "Created patient", patientService.save(patient));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDTO<Object>> getAll() {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(200, false, "Registered patients", patientService.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}