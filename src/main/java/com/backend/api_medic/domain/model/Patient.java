package com.backend.api_medic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Integer id;
    private FullName fullName;
    private LocalDateTime birthday;
    private String address;
    private String telephone;
    private String email;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}