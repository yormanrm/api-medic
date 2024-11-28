package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.Credential;

public interface ICredentialRepository {
    Credential findByUsername(String username);
    void updateCredential(Credential credential);
}