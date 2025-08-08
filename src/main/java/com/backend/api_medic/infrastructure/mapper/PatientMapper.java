package com.backend.api_medic.infrastructure.mapper;

import com.backend.api_medic.domain.model.Patient;
import com.backend.api_medic.infrastructure.entity.PatientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "birthday", target = "birthday"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "telephone", target = "telephone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "dateUpdated", target = "dateUpdated"),
            @Mapping(source = "logicallyRemoved", target = "logicallyRemoved")
    })
    Patient toPatient(PatientEntity patientEntity);

    Iterable<Patient> toPatients(Iterable<PatientEntity> patientEntities);

    @InheritInverseConfiguration
    PatientEntity toPatientEntity(Patient patient);
}