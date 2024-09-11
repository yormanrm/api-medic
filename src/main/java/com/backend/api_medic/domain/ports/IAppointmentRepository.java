package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.Appointment;

public interface IAppointmentRepository {
    Appointment save(Appointment appointment);
    Iterable<Appointment> findAll();
    Appointment findById(Integer id);
    Iterable<Appointment> findByPatientId(Integer id);
    Iterable<Appointment> findByDoctorId(Integer id);
    void updateStatusById(Integer id, String status);
}