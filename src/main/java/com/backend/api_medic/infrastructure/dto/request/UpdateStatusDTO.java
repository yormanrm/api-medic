package com.backend.api_medic.infrastructure.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStatusDTO {

    @NotNull(message = "Pacient ID is required")
    private Integer id;

    @NotBlank(message = "Status is required")
    @Pattern(
            regexp = "SCHEDULED|CANCELLED|COMPLETED",
            message = "Status must be SCHEDULED, CANCELLED, or COMPLETED."
    )
    private String status;
}