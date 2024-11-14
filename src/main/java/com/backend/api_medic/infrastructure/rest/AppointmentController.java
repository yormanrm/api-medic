package com.backend.api_medic.infrastructure.rest;

import com.backend.api_medic.application.AppointmentService;
import com.backend.api_medic.domain.model.Appointment;
import com.backend.api_medic.infrastructure.dto.response.ApiResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment")
@CrossOrigin(origins = "http://localhost:4200/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Object>> register(@Valid @RequestBody Appointment appointment) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                201,
                false,
                "Created appointment",
                appointmentService.save(appointment)
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDTO<Object>> getAll() {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Appointments found",
                appointmentService.findAll()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-byID")
    public ResponseEntity<ApiResponseDTO<Object>> getByID(@RequestParam Integer id) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Appointment found with ID equals to " + id,
                appointmentService.findById(id)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-byPatientID")
    public ResponseEntity<ApiResponseDTO<Object>> getByPatientID(@RequestParam Integer id) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Appointment found with Patient ID equals to " + id,
                appointmentService.findByPatientId(id)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-byDoctorID")
    public ResponseEntity<ApiResponseDTO<Object>> getByDoctorID(@RequestParam Integer id) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Appointment found with Doctor ID equals to " + id,
                appointmentService.findByDoctorId(id)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}