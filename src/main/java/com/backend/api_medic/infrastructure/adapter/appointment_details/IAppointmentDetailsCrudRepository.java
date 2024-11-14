package com.backend.api_medic.infrastructure.adapter.appointment_details;

import com.backend.api_medic.infrastructure.entity.AppointmentDetailsEntity;
import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IAppointmentDetailsCrudRepository extends CrudRepository<AppointmentDetailsEntity, Integer> {
    Optional<AppointmentDetailsEntity> findByAppointmentEntity(AppointmentEntity appointmentEntity);
}