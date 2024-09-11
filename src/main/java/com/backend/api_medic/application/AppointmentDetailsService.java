package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.AppointmentDetails;
import com.backend.api_medic.domain.ports.IAppointmentDetailsRepository;

public class AppointmentDetailsService {
    private final IAppointmentDetailsRepository iAppointmentDetailsRepository;

    public AppointmentDetailsService(IAppointmentDetailsRepository iAppointmentDetailsRepository) {
        this.iAppointmentDetailsRepository = iAppointmentDetailsRepository;
    }

    public AppointmentDetails save(AppointmentDetails appointmentDetails) {
        return iAppointmentDetailsRepository.save(appointmentDetails);
    }
}
