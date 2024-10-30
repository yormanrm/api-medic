package com.backend.api_medic.infrastructure.adapter.appointment_details;

import com.backend.api_medic.domain.model.AppointmentDetails;
import com.backend.api_medic.domain.ports.IAppointmentDetailsRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AppointmentDetailsCrudRepositoryImpl implements IAppointmentDetailsRepository {
    @Override
    public AppointmentDetails save(AppointmentDetails appointmentDetails) {
        return null;
    }
}
