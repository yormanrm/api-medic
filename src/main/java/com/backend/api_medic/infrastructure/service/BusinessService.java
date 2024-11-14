package com.backend.api_medic.infrastructure.service;

import com.backend.api_medic.domain.model.Appointment;
import com.backend.api_medic.domain.model.Doctor;
import com.backend.api_medic.infrastructure.adapter.appointment.IAppointmentCrudRepository;
import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import com.backend.api_medic.infrastructure.entity.DoctorEntity;
import com.backend.api_medic.infrastructure.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessService {

    @Autowired
    private IAppointmentCrudRepository iAppointmentCrudRepository;

    public void validateDoctorAvailability(Appointment appointment) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(appointment.getDoctorId());
        Optional<AppointmentEntity> optionalAppointmentEntity = iAppointmentCrudRepository.findByDoctorEntityAndAppointmentDate(doctorEntity, appointment.getAppointmentDate());
        if (optionalAppointmentEntity.isPresent()) {
            throw new BusinessException("The doctor already has an appointment at " + appointment.getAppointmentDate());
        }
    }

}