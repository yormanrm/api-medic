package com.backend.api_medic.infrastructure.mapper;

import com.backend.api_medic.domain.model.Doctor;
import com.backend.api_medic.domain.model.Patient;
import com.backend.api_medic.infrastructure.entity.DoctorEntity;
import com.backend.api_medic.infrastructure.entity.PatientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fullName", target = "fullName"),
            @Mapping(source = "birthday", target = "birthday"),
            @Mapping(source = "specialty", target = "specialty"),
            @Mapping(source = "professionalLicense", target = "professionalLicense"),
            @Mapping(source = "telephone", target = "telephone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "dateUpdated", target = "dateUpdated")
    })
    Doctor toDoctor(DoctorEntity doctorEntity);

    Iterable<Doctor> toDoctors(Iterable<DoctorEntity> doctorEntities);

    @InheritInverseConfiguration
    DoctorEntity toDoctorEntity(Doctor doctor);
}