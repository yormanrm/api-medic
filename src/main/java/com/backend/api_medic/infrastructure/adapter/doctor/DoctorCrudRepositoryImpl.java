package com.backend.api_medic.infrastructure.adapter.doctor;

import com.backend.api_medic.domain.model.Doctor;
import com.backend.api_medic.domain.ports.IDoctorRepository;
import com.backend.api_medic.infrastructure.entity.DoctorEntity;
import com.backend.api_medic.infrastructure.exception.EmptyIterableException;
import com.backend.api_medic.infrastructure.exception.ResourceAlreadyExistsException;
import com.backend.api_medic.infrastructure.exception.ResourceNotFoundException;
import com.backend.api_medic.infrastructure.mapper.DoctorMapper;
import com.backend.api_medic.infrastructure.utils.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DoctorCrudRepositoryImpl implements IDoctorRepository {

    @Autowired
    private IDoctorCrudRepository iDoctorCrudRepository;

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Doctor save(Doctor doctor) {
        Optional<DoctorEntity> optionalDoctorEntity = iDoctorCrudRepository.findByFullName(doctor.getFullName());
        if (optionalDoctorEntity.isPresent()) {
            throw new ResourceAlreadyExistsException("A doctor has been registered with name " + doctor.getFullName());
        } else {
            return doctorMapper.toDoctor(iDoctorCrudRepository.save(doctorMapper.toDoctorEntity(doctor)));
        }
    }

    @Override
    public Iterable<Doctor> findAll() {
        Iterable<Doctor> doctors = doctorMapper.toDoctors(iDoctorCrudRepository.findAll());
        if(IterableUtils.isEmpty(doctors)) {
            throw new EmptyIterableException("There are no registered doctors");
        } else {
            return doctors;
        }
    }

    @Override
    public Doctor findById(Integer id) {
        Optional<DoctorEntity> optionalDoctorEntity = iDoctorCrudRepository.findById(id);
        if(optionalDoctorEntity.isPresent()) {
            return doctorMapper.toDoctor(optionalDoctorEntity.get());
        } else {
            throw new ResourceNotFoundException("Doctor with ID " + id + " not found");
        }
    }

    @Override
    public Iterable<Doctor> searchBySomeTextfield(String textfield) {
        Iterable<Doctor> doctors = doctorMapper.toDoctors(iDoctorCrudRepository.searchBySomeTextfield(textfield));
        if(IterableUtils.isEmpty(doctors)) {
            throw new EmptyIterableException("There are no registered doctors with a full name, specialty or professional license similar to " + textfield);
        } else {
            return doctors;
        }
    }
}
