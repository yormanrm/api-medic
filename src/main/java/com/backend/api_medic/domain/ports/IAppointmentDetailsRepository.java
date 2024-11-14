package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.AppointmentDetails;

public interface IAppointmentDetailsRepository {
    AppointmentDetails findByAppointmentId(Integer id);

    AppointmentDetails updateAppointmentDetails(AppointmentDetails appointmentDetails);
}