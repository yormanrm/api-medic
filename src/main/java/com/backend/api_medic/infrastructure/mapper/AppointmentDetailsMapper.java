package com.backend.api_medic.infrastructure.mapper;

import com.backend.api_medic.domain.model.Appointment;
import com.backend.api_medic.domain.model.AppointmentDetails;
import com.backend.api_medic.infrastructure.entity.AppointmentDetailsEntity;
import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AppointmentDetailsMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "appointmentEntity.id", target = "appointmentId"),
            @Mapping(source = "diagnosis", target = "diagnosis"),
            @Mapping(source = "treatment", target = "treatment"),
            @Mapping(source = "notes", target = "notes")
    })
    AppointmentDetails toAppointmentDetails(AppointmentDetailsEntity appointmentDetailsEntity);

    Iterable<AppointmentDetails> toAppointmentDetailsIterable(Iterable<AppointmentDetailsEntity> appointmentDetailsEntities);

    @InheritInverseConfiguration
    AppointmentDetailsEntity toAppointmentDetailsEntity(AppointmentDetails appointmentDetails);
}
