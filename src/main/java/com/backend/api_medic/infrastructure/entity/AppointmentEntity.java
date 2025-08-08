package com.backend.api_medic.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private PatientEntity patientEntity;

    @ManyToOne
    private DoctorEntity doctorEntity;

    private String reason;
    private String status;
    private LocalDateTime appointmentDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    private boolean logicallyRemoved;
}