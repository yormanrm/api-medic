package com.backend.api_medic.infrastructure.adapter.appointment;

import com.backend.api_medic.domain.model.Appointment;
import com.backend.api_medic.domain.model.AppointmentDetails;
import com.backend.api_medic.domain.ports.IAppointmentDetailsRepository;
import com.backend.api_medic.domain.ports.IAppointmentRepository;
import com.backend.api_medic.infrastructure.adapter.appointment_details.IAppointmentDetailsCrudRepository;
import com.backend.api_medic.infrastructure.entity.AppointmentDetailsEntity;
import com.backend.api_medic.infrastructure.entity.AppointmentEntity;
import com.backend.api_medic.infrastructure.entity.DoctorEntity;
import com.backend.api_medic.infrastructure.entity.PatientEntity;
import com.backend.api_medic.infrastructure.exception.BusinessException;
import com.backend.api_medic.infrastructure.exception.EmptyIterableException;
import com.backend.api_medic.infrastructure.exception.ResourceNotFoundException;
import com.backend.api_medic.infrastructure.mapper.AppointmentMapper;
import com.backend.api_medic.infrastructure.service.BusinessService;
import com.backend.api_medic.infrastructure.utils.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class AppointmentCrudRepositoryImpl implements IAppointmentRepository {

    @Autowired
    private IAppointmentCrudRepository iAppointmentCrudRepository;

    @Autowired
    private IAppointmentDetailsCrudRepository iAppointmentDetailsCrudRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Autowired
    private BusinessService businessService;

    @Override
    public Appointment save(Appointment appointment) {
        businessService.validateDoctorAvailability(appointment);

        AppointmentEntity savedAppointmentEntity = iAppointmentCrudRepository.save(appointmentMapper.toAppointmentEntity(appointment));
        Appointment savedAppointment = appointmentMapper.toAppointment(savedAppointmentEntity);

        AppointmentDetailsEntity appointmentDetailsEntity = new AppointmentDetailsEntity();
        appointmentDetailsEntity.setAppointmentEntity(savedAppointmentEntity);
        iAppointmentDetailsCrudRepository.save(appointmentDetailsEntity);


        return savedAppointment;
    }

    @Override
    public Iterable<Appointment> findAll() {
        Iterable<Appointment> appointments = appointmentMapper.toAppointments(iAppointmentCrudRepository.findAll());
        if(IterableUtils.isEmpty(appointments)) {
            throw new EmptyIterableException("There are no registered appointments");
        } else {
            return appointments;
        }
    }

    @Override
    public Appointment findById(Integer id) {
        Optional<AppointmentEntity> optionalAppointmentEntity = iAppointmentCrudRepository.findById(id);
        if(optionalAppointmentEntity.isPresent()) {
            return appointmentMapper.toAppointment(optionalAppointmentEntity.get());
        } else {
            throw new ResourceNotFoundException("Appointment with ID " + id + " not found");
        }
    }

    @Override
    public Iterable<Appointment> findByPatientId(Integer id) {
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(id);
        Iterable<Appointment> appointments = appointmentMapper.toAppointments(iAppointmentCrudRepository.findByPatientEntity(patientEntity));
        if(IterableUtils.isEmpty(appointments)) {
            throw new EmptyIterableException("There are no registered appointments with Patient ID " + id);
        } else {
            return appointments;
        }
    }

    @Override
    public Iterable<Appointment> findByDoctorId(Integer id) {
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setId(id);
        Iterable<Appointment> appointments = appointmentMapper.toAppointments(iAppointmentCrudRepository.findByDoctorEntity(doctorEntity));
        if(IterableUtils.isEmpty(appointments)) {
            throw new EmptyIterableException("There are no registered appointments with Doctor ID " + id);
        } else {
            return appointments;
        }
    }

    @Override
    @Transactional
    public void updateStatusById(Integer id, String status) {
        Optional<AppointmentEntity> optionalAppointmentEntity = iAppointmentCrudRepository.findById(id);
        if(optionalAppointmentEntity.isPresent()){
            Appointment appointment = appointmentMapper.toAppointment(optionalAppointmentEntity.get());
            appointment.setStatus(status);
            iAppointmentCrudRepository.save(appointmentMapper.toAppointmentEntity(appointment));
        } else {
            throw new ResourceNotFoundException("Appointment with ID " + id + " not found");
        }
    }
}