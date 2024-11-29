package com.backend.api_medic.domain.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Credential {
    private Integer id;

    @NotNull(message = "Employee ID is required")
    private Integer employeeId;

    @NotBlank(message = "Username is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9.]+$",
            message = "Username can only contain letters, numbers and periods (.)"
    )
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9_.-]+$",
            message = "Password can only contain letters, numbers, underscores (_), hyphens (-), and periods (.)"
    )
    @Size(
            min = 8,
            message = "Password must have at least 8 characters"
    )
    private String password;

    @NotBlank(message = "Role is required")
    @Pattern(
            regexp = "ADMIN|DOCTOR|PHARMACIST",
            message = "Role must be ADMIN, DOCTOR, or PHARMACIST."
    )
    private String role;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}