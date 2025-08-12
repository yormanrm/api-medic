package com.backend.api_medic.infrastructure.adapter.credential;

import com.backend.api_medic.infrastructure.entity.CredentialEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ICredentialCrudRepository extends CrudRepository<CredentialEntity, Integer> {
    Optional<CredentialEntity> findByUsername(String username);
    Optional<CredentialEntity> findByEmployeeIdAndRole(Integer employeeId, String role);
}