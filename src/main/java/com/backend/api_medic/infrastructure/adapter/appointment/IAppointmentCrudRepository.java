package com.backend.api_medic.infrastructure.adapter.appointment;

import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import com.backend.api_medic.infrastructure.entity.DoctorEntity;
import com.backend.api_medic.infrastructure.entity.PatientEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IAppointmentCrudRepository extends CrudRepository<AppointmentEntity, Integer> {

    Optional<AppointmentEntity> findByDoctorEntityAndAppointmentDate(DoctorEntity doctorEntity, LocalDateTime appointmentDate);

    Iterable<AppointmentEntity> findByPatientEntity(PatientEntity patientEntity);

    Iterable<AppointmentEntity> findByDoctorEntity(DoctorEntity doctorEntity);

}