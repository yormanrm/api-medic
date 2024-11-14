package com.backend.api_medic.infrastructure.rest;

import com.backend.api_medic.application.AppointmentDetailsService;
import com.backend.api_medic.domain.model.AppointmentDetails;
import com.backend.api_medic.infrastructure.dto.request.UpdateStatusDTO;
import com.backend.api_medic.infrastructure.dto.response.ApiResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointment/details")
@CrossOrigin(origins = "http://localhost:4200/")
public class AppointmentDetailsController {

    @Autowired
    private AppointmentDetailsService appointmentDetailsService;

    @GetMapping("/get-byAppointmentID")
    public ResponseEntity<ApiResponseDTO<Object>> getByID(@RequestParam Integer id) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Appointment details found with appointment ID equals to " + id,
                appointmentDetailsService.findByAppointmentId(id)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponseDTO<Object>> updateAppointmentDetails(@Valid @RequestBody AppointmentDetails appointmentDetails) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Appointment details with ID equals to " + appointmentDetails.getId() +" updated",
                appointmentDetailsService.updateAppointmentDetails(appointmentDetails)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}