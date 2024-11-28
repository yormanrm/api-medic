package com.backend.api_medic.infrastructure.mapper;

import com.backend.api_medic.domain.model.Credential;
import com.backend.api_medic.infrastructure.entity.CredentialEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CredentialMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "employeeId", target = "employeeId"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "role"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "dateUpdated", target = "dateUpdated")
    })

    Credential toCredential(CredentialEntity credentialEntity);

    @InheritInverseConfiguration
    CredentialEntity toCredentialEntity(Credential credential);
}