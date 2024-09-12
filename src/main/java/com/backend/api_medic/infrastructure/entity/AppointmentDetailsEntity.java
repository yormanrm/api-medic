package com.backend.api_medic.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private AppointmentEntity appointmentEntity;

    @Lob
    private String diagnosis;

    @Lob
    private String treatment;

    @Lob
    private String notes;
}