package com.backend.api_medic.infrastructure.adapter.appointment_details;

import com.backend.api_medic.domain.model.AppointmentDetails;
import com.backend.api_medic.domain.ports.IAppointmentDetailsRepository;
import com.backend.api_medic.infrastructure.entity.AppointmentDetailsEntity;
import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import com.backend.api_medic.infrastructure.exception.ResourceNotFoundException;
import com.backend.api_medic.infrastructure.mapper.AppointmentDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AppointmentDetailsCrudRepositoryImpl implements IAppointmentDetailsRepository {

    @Autowired
    private IAppointmentDetailsCrudRepository iAppointmentDetailsCrudRepository;

    @Autowired
    private AppointmentDetailsMapper appointmentDetailsMapper;

    @Override
    public AppointmentDetails findByAppointmentId(Integer id) {
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        appointmentEntity.setId(id);
        Optional<AppointmentDetailsEntity> optionalAppointmentDetailsEntity = iAppointmentDetailsCrudRepository.findByAppointmentEntity(appointmentEntity);
        if(optionalAppointmentDetailsEntity.isPresent()){
            return appointmentDetailsMapper.toAppointmentDetails(optionalAppointmentDetailsEntity.get());
        } else {
            throw new ResourceNotFoundException("Appointment details with Appointment ID " + id + " not found");
        }
    }

    @Override
    public AppointmentDetails updateAppointmentDetails(AppointmentDetails appointmentDetails) {
        return appointmentDetailsMapper.toAppointmentDetails(iAppointmentDetailsCrudRepository.save(appointmentDetailsMapper.toAppointmentDetailsEntity(appointmentDetails)));
    }
}