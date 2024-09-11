package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.AppointmentDetails;

public interface IAppointmentDetailsRepository {
    AppointmentDetails save(AppointmentDetails appointmentDetails);
}