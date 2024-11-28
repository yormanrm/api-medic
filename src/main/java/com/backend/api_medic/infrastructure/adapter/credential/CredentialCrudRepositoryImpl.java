package com.backend.api_medic.infrastructure.adapter.credential;

import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.domain.ports.ICredentialRepository;
import com.backend.api_medic.infrastructure.entity.CredentialEntity;
import com.backend.api_medic.infrastructure.exception.BusinessException;
import com.backend.api_medic.infrastructure.exception.CredentialException;
import com.backend.api_medic.infrastructure.exception.ResourceAlreadyExistsException;
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
    public Credential findByUsername(String username) {
        Optional<CredentialEntity> optionalCredentialEntity = iCredentialCrudRepository.findByUsername(username);
        if(optionalCredentialEntity.isPresent()){
            return credentialMapper.toCredential(optionalCredentialEntity.get());
        } else {
            throw new CredentialException("User with username: " + username + " not found.");
        }
    }

    @Override
    public void updateCredential(Credential credential) {
        Optional<CredentialEntity> optionalCredentialEntity = iCredentialCrudRepository.findById(credential.getId());
        if(optionalCredentialEntity.isPresent()){
            if(optionalCredentialEntity.get().getUsername().equals(credential.getUsername())){
                throw new ResourceAlreadyExistsException("Credential with username: " + credential.getUsername() + " is alredy register.");
            } else {
                iCredentialCrudRepository.save(credentialMapper.toCredentialEntity(credential));
            }
        } else {
            throw new ResourceNotFoundException("Credential with ID: " + credential.getId() + " not found.");
        }
    }
}