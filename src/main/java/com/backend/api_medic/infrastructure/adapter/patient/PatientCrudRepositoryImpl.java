package com.backend.api_medic.infrastructure.adapter.patient;

import com.backend.api_medic.domain.model.Patient;
import com.backend.api_medic.domain.ports.IPatientRepository;
import com.backend.api_medic.infrastructure.entity.PatientEntity;
import com.backend.api_medic.infrastructure.exception.EmptyIterableException;
import com.backend.api_medic.infrastructure.exception.PatientAlreadyExistsException;
import com.backend.api_medic.infrastructure.exception.ResourceNotFoundException;
import com.backend.api_medic.infrastructure.mapper.PatientMapper;
import com.backend.api_medic.infrastructure.utils.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PatientCrudRepositoryImpl implements IPatientRepository {

    @Autowired
    private IPatientCrudRepository iPatientCrudRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public Patient save(Patient patient) {
        Optional<PatientEntity> optionalPatientEntity = iPatientCrudRepository.findByFullName(patient.getFullName());
        if (optionalPatientEntity.isPresent()) {
            throw new PatientAlreadyExistsException("A patient has been registered with name " + patient.getFullName());
        } else {
            return patientMapper.toPatient(iPatientCrudRepository.save(patientMapper.toPatientEntity(patient)));
        }
    }

    @Override
    public Iterable<Patient> findAll() {
        Iterable<Patient> patients = patientMapper.toPatients(iPatientCrudRepository.findAll());
        if (IterableUtils.isEmpty(patients)) {
            throw new EmptyIterableException("There are no registered patients");
        } else {
            return patients;
        }
    }

    @Override
    public Patient findById(Integer id) {
        Optional<PatientEntity> patient = iPatientCrudRepository.findById(id);
        if (patient.isPresent()) {
            return patientMapper.toPatient(patient.get());
        } else {
            throw new ResourceNotFoundException("Patient with ID " + id + " not found");
        }
    }

    @Override
    public Iterable<Patient> searchByFullName(String fullName) {
        Iterable<Patient> patients = patientMapper.toPatients(iPatientCrudRepository.searchByFullName(fullName));
        if (IterableUtils.isEmpty(patients)) {
            throw new EmptyIterableException("There are no registered patients with a full name similar to " + fullName);
        } else {
            return patients;
        }
    }

}