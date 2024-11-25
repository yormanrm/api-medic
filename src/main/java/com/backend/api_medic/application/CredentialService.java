package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.domain.ports.ICredentialRepository;

public class CredentialService {

    private final ICredentialRepository iCredentialRepository;

    public CredentialService(ICredentialRepository iCredentilaRepository) {
        this.iCredentialRepository = iCredentilaRepository;
    }

    public Credential findByEmail(String email, String password) {
        return iCredentialRepository.findByEmail(email, password);
    }

    public void updateCredential(Credential credential) {
        this.iCredentialRepository.updateCredential(credential);
    }

}