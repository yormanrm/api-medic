package com.backend.api_medic.infrastructure.dto.request;

import com.backend.api_medic.domain.model.Employee;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    @NotNull(message = "Employee is required")
    @Valid
    private Employee employee;
    @NotBlank(message = "Role is required")
    @Pattern(
            regexp = "ADMIN|DOCTOR|PHARMACIST",
            message = "Role must be ADMIN, DOCTOR, or PHARMACIST."
    )
    private String role;
}