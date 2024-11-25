package com.backend.api_medic.infrastructure.adapter.credential;

import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.domain.ports.ICredentialRepository;
import com.backend.api_medic.infrastructure.entity.CredentialEntity;
import com.backend.api_medic.infrastructure.exception.ResourceNotFoundException;
import com.backend.api_medic.infrastructure.mapper.CredentialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CredentialCrudRepositoryImpl implements ICredentialRepository {
    @Autowired
    private ICredentialCrudRepository iCredentialCrudRepository;

    @Autowired
    private CredentialMapper credentialMapper;

    @Override
    public Credential findByEmail(String email, String password) {
        Optional<CredentialEntity> optionalCredentialEntity = iCredentialCrudRepository.findByEmail(email);
        if(optionalCredentialEntity.isPresent()){
            Credential credential =  credentialMapper.toCredential(optionalCredentialEntity.get());
            if(credential.getPassword().equals(password)){
                return credential;
            } else {
                throw new ResourceNotFoundException("Password is not correct.");
            }
        } else {
            throw new ResourceNotFoundException("User with email: " + email + " not found.");
        }
    }

    @Override
    public void updateCredential(Credential credential) {
        Optional<CredentialEntity> optionalCredentialEntity = iCredentialCrudRepository.findById(credential.getId());
        if(optionalCredentialEntity.isPresent()){
            iCredentialCrudRepository.save(credentialMapper.toCredentialEntity(credential));
        } else {
            throw new ResourceNotFoundException("Credential with ID: " + credential.getId() + " not found.");
        }
    }
}