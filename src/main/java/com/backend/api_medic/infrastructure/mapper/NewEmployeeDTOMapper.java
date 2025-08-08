package com.backend.api_medic.infrastructure.mapper;

import com.backend.api_medic.infrastructure.dto.response.AppointmentResponseDTO;
import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface NewEmployeeDTOMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "patientEntity.id", target = "patientId"),
            @Mapping(source = "doctorEntity.id", target = "doctorId"),
            @Mapping(source = "patientEntity.fullName", target = "patientName"),
            @Mapping(source = "doctorEntity.fullName", target = "doctorName"),
            @Mapping(source = "reason", target = "reason"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "appointmentDate", target = "appointmentDate"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "dateUpdated", target = "dateUpdated")
    })
    AppointmentResponseDTO toAppointmentResponseDTO(AppointmentEntity appointmentEntity);

    Iterable<AppointmentResponseDTO> toAppointmentsResponseDTO(Iterable<AppointmentEntity> appointmentEntities);
}