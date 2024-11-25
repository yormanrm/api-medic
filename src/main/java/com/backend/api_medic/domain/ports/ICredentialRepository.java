package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.Credential;

public interface ICredentialRepository {
    Credential findByEmail(String email, String password);
    void updateCredential(Credential credential);
}