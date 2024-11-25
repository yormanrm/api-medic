package com.backend.api_medic.domain.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    private Integer id;

    @NotBlank(message = "Email is required")
    @Email(message = "Email wrong structure")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(
            min = 8,
            max = 16,
            message = "Password must be between 8 and 16 characters"
    )
    private String password;

    @NotBlank(message = "Role is required")
    @Pattern(
            regexp = "ADMIN|DOCTOR|PHARMACY",
            message = "Role must be ADMIN, DOCTOR, or PHARMACY."
    )
    private String role;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}