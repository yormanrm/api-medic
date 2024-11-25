package com.backend.api_medic.infrastructure.rest;

import com.backend.api_medic.application.CredentialService;
import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.infrastructure.dto.request.CredentialDTO;
import com.backend.api_medic.infrastructure.dto.response.ApiResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class CredentialController {
    @Autowired
    private CredentialService credentialService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<Object>> logIn(@Valid @RequestBody CredentialDTO credentialDTO) {
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "User authenticated ",
                credentialService.findByEmail(credentialDTO.getEmail(), credentialDTO.getPassword())
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update-credential")
    public ResponseEntity<ApiResponseDTO<Object>> updateCredential(@Valid @RequestBody Credential credential) {
        credentialService.updateCredential(credential);
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Credential updated ",
                null
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}