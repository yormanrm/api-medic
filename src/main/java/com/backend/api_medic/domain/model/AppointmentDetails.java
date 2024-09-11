package com.backend.api_medic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetails {
    private Integer id;
    private Integer appointmentId;
    private String diagnosis;
    private String treatment;
    private String notes;
}