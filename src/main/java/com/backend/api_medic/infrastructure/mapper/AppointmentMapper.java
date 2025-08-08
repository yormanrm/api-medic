package com.backend.api_medic.infrastructure.mapper;

import com.backend.api_medic.domain.model.Appointment;
import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "patientEntity.id", target = "patientId"),
            @Mapping(source = "doctorEntity.id", target = "doctorId"),
            @Mapping(source = "reason", target = "reason"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "appointmentDate", target = "appointmentDate"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "dateUpdated", target = "dateUpdated"),
            @Mapping(source = "logicallyRemoved", target = "logicallyRemoved")
    })
    Appointment toAppointment(AppointmentEntity appointmentEntity);

    Iterable<Appointment> toAppointments(Iterable<AppointmentEntity> appointmentEntities);

    @InheritInverseConfiguration
    AppointmentEntity toAppointmentEntity(Appointment appointment);
    Iterable<AppointmentEntity> toAppointmentsEntities(Iterable<Appointment> appointments);
}