package com.backend.api_medic.infrastructure.adapter.appointment;

import com.backend.api_medic.domain.model.Appointment;
import com.backend.api_medic.domain.ports.IAppointmentRepository;

public class AppointmentCrudRepositoryImpl implements IAppointmentRepository {
    @Override
    public Appointment save(Appointment appointment) {
        return null;
    }

    @Override
    public Iterable<Appointment> findAll() {
        return null;
    }

    @Override
    public Appointment findById(Integer id) {
        return null;
    }

    @Override
    public Iterable<Appointment> findByPatientId(Integer id) {
        return null;
    }

    @Override
    public Iterable<Appointment> findByDoctorId(Integer id) {
        return null;
    }

    @Override
    public void updateStatusById(Integer id, String status) {

    }
}
