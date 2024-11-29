package com.backend.api_medic.domain.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    @NotBlank(message = "Full name is required")
    @Size(
            min = 2,
            max = 100,
            message = "Full name must be between 2 and 100 characters"
    )
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Full name can only contain letters and spaces"
    )
    private String fullName;

    @NotNull(message = "Date of birth is required")
    @Past(message = "The date of birth must be in the past")
    private LocalDateTime birthday;

    @NotBlank(message = "Academic degree is required")
    @Size(
            min = 2,
            max = 50,
            message = "Academic degree must be between 2 and 255 characters"
    )
    @Pattern(
            regexp = "^[a-zA-Z\\s]+$",
            message = "Specialty can only contain letters and spaces"
    )
    private String academicDegree;

    @NotBlank(message = "Professional license is required")
    @Size(
            min = 2,
            max = 8,
            message = "Professional license must be between 2 and 8 characters"
    )
    private String professionalLicense;

    @NotBlank(message = "Telephone is required")
    @Pattern(
            regexp = "^\\+52\\d{10}$",
            message = "Telephone number must follow the format +52XXXXXXXXXX"
    )
    private String telephone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email wrong structure")
    private String email;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;
}