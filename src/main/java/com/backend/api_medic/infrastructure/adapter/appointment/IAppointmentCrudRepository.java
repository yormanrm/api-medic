package com.backend.api_medic.infrastructure.adapter.appointment;

import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAppointmentCrudRepository extends CrudRepository<AppointmentEntity, Integer> {
}