package com.backend.api_medic.infrastructure.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class NewEmployeeDTO {
    private String fullName;
    private String username;
    private String password;
    private String role;
}