package com.backend.api_medic.infrastructure.rest;

import com.backend.api_medic.application.CredentialService;
import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.infrastructure.dto.request.CredentialDTO;
import com.backend.api_medic.infrastructure.dto.response.ApiResponseDTO;
import com.backend.api_medic.infrastructure.exception.CredentialException;
import com.backend.api_medic.infrastructure.jwt.JwtTokenProvider;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<Object>> logIn(@Valid @RequestBody CredentialDTO credentialDTO) {
        try {
            Credential credential = credentialService.findByUsername(credentialDTO.getUsername());
            if (bCryptPasswordEncoder.matches(credentialDTO.getPassword(), credential.getPassword())) {
                String token = jwtTokenProvider.generateToken(credential);
                Map<String, Object> generatedToken = new HashMap<>();
                generatedToken.put("accessToken", token);
                generatedToken.put("tokenType", "Bearer");
                generatedToken.put("userName", credential.getUsername());
                generatedToken.put("role", credential.getRole());
                ApiResponseDTO<Object> response = new ApiResponseDTO<>(200,
                        false,
                        "User authenticated",
                        generatedToken
                );
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new CredentialException("Incorrect password");
            }
        } catch (CredentialException | JOSEException ex) {
            ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                    401,
                    true,
                    ex.getMessage(),
                    null
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/update-credential")
    public ResponseEntity<ApiResponseDTO<Object>> updateCredential(@Valid @RequestBody Credential credential) {
        credentialService.updateCredential(credential);
        ApiResponseDTO<Object> response = new ApiResponseDTO<>(
                200,
                false,
                "Credential updated",
                null
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}