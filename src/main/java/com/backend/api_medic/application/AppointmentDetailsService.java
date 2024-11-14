package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.AppointmentDetails;
import com.backend.api_medic.domain.ports.IAppointmentDetailsRepository;

public class AppointmentDetailsService {
    private final IAppointmentDetailsRepository iAppointmentDetailsRepository;

    public AppointmentDetailsService(IAppointmentDetailsRepository iAppointmentDetailsRepository) {
        this.iAppointmentDetailsRepository = iAppointmentDetailsRepository;
    }

    public AppointmentDetails findByAppointmentId(Integer id) {
        return iAppointmentDetailsRepository.findByAppointmentId(id);
    }

    public AppointmentDetails updateAppointmentDetails(AppointmentDetails appointmentDetails){
        return iAppointmentDetailsRepository.updateAppointmentDetails(appointmentDetails);
    }
}