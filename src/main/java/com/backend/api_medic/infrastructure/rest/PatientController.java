package com.backend.api_medic.infrastructure.rest;

import com.backend.api_medic.application.PatientService;
import com.backend.api_medic.domain.model.Patient;
import com.backend.api_medic.infrastructure.dto.ApiResponseDTO;
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
    public ResponseEntity<ApiResponseDTO<Object>> register(@RequestBody Patient patient) {
        Patient savedPatient = patientService.save(patient);
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(201, false, "Created patient", savedPatient);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}