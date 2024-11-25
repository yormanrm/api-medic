package com.backend.api_medic.infrastructure.adapter.credential;

import com.backend.api_medic.infrastructure.entity.CredentialEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ICredentialCrudRepository extends CrudRepository<CredentialEntity, Integer> {
    Optional<CredentialEntity> findByEmail(String email);
}