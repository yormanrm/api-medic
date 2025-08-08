package com.backend.api_medic.domain.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    private Integer id;

    @NotNull(message = "Pacient ID is required")
    private Integer patientId;

    @NotNull(message = "Doctor ID is required")
    private Integer doctorId;

    @NotBlank(message = "Reason is required")
    @Size(
            min = 2,
            max = 255,
            message = "Reason must be between 2 and 255 characters"
    )
    private String reason;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "SCHEDULED|CANCELLED|COMPLETED",
            message = "Status must be SCHEDULED, CANCELLED, or COMPLETED."
    )
    private String status;

    @NotNull(message = "Appointment date is required")
    @Future(message = "The appointment date must be in the future.")
    private LocalDateTime appointmentDate;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;
    private boolean logicallyRemoved;
}