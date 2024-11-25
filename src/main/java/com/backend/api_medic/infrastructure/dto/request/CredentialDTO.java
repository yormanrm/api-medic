package com.backend.api_medic.infrastructure.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDTO {
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
}
