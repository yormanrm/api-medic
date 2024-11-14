package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.Appointment;
import com.backend.api_medic.infrastructure.dto.response.AppointmentResponseDTO;

public interface IAppointmentRepository {
    Appointment save(Appointment appointment);

    Iterable<AppointmentResponseDTO> findAll();

    AppointmentResponseDTO findById(Integer id);

    Iterable<AppointmentResponseDTO> findByPatientId(Integer id);

    Iterable<AppointmentResponseDTO> findByDoctorId(Integer id);

    void updateStatusById(Integer id, String status);
}