package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.domain.ports.ICredentialRepository;

public class CredentialService {

    private final ICredentialRepository iCredentialRepository;

    public CredentialService(ICredentialRepository iCredentilaRepository) {
        this.iCredentialRepository = iCredentilaRepository;
    }

    public Credential findByUsername(String username) {
        return iCredentialRepository.findByUsername(username);
    }

    public void updateCredential(Credential credential) {
        this.iCredentialRepository.updateCredential(credential);
    }

}