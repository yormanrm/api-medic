package com.backend.api_medic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private Integer id;
    private FullName fullName;
    private String specialty;
    private String professionalLicense;
    private String telephone;
    private String email;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}