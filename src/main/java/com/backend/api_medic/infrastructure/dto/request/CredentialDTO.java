package com.backend.api_medic.infrastructure.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDTO {
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
}
