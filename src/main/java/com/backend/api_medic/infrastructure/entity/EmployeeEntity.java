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
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private LocalDateTime birthday;
    private String academicDegree;
    private String professionalLicense;
    private String telephone;
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    private boolean logicallyRemoved;
}