package com.backend.api_medic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Integer id;
    private Integer patientId;
    private Integer doctorId;
    private String reason;
    private String status;
    private LocalDateTime appointmentDate;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}