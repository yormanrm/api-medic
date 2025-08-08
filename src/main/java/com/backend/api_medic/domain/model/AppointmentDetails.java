package com.backend.api_medic.domain.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetails {

    private Integer id;

    @NotNull(message = "Appointment ID is required")
    private Integer appointmentId;

    @Size(
            min = 2,
            max = 255,
            message = "Diagnosis must be between 2 and 255 characters"
    )
    private String diagnosis;

    @Size(
            min = 2,
            max = 255,
            message = "Diagnosis must be between 2 and 255 characters"
    )
    private String treatment;

    @Size(
            min = 2,
            max = 255,
            message = "Diagnosis must be between 2 and 255 characters"
    )
    private String notes;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;
    private boolean logicallyRemoved;
}