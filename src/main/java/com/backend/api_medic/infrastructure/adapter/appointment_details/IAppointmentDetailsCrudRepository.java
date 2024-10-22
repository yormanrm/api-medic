package com.backend.api_medic.infrastructure.adapter.appointment_details;

import com.backend.api_medic.infrastructure.entity.AppointmentDetailsEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAppointmentDetailsCrudRepository extends CrudRepository<AppointmentDetailsEntity, Integer> {
}