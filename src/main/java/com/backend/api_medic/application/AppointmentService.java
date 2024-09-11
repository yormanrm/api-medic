package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.Appointment;
import com.backend.api_medic.domain.ports.IAppointmentRepository;

public class AppointmentService {
    private final IAppointmentRepository iAppointmentRepository;

    public AppointmentService(IAppointmentRepository iAppointmentRepository) {
        this.iAppointmentRepository = iAppointmentRepository;
    }

    public Appointment save(Appointment appointment) {
        return iAppointmentRepository.save(appointment);
    }

    public Iterable<Appointment> findAll() {
        return iAppointmentRepository.findAll();
    }

    public Appointment findById(Integer id) {
        return iAppointmentRepository.findById(id);
    }

    public Iterable<Appointment> findByPatientId(Integer id) {
        return iAppointmentRepository.findByPatientId(id);
    }

    public Iterable<Appointment> findByDoctorId(Integer id) {
        return iAppointmentRepository.findByDoctorId(id);
    }

    public void updateStatusById(Integer id, String status) {
        this.iAppointmentRepository.updateStatusById(id, status);
    }
}