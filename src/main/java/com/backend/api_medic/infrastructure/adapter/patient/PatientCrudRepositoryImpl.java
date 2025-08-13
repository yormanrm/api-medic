package com.backend.api_medic.infrastructure.adapter.patient;

import com.backend.api_medic.domain.model.Patient;
import com.backend.api_medic.domain.ports.IPatientRepository;
import com.backend.api_medic.infrastructure.entity.PatientEntity;
import com.backend.api_medic.infrastructure.exception.EmptyIterableException;
import com.backend.api_medic.infrastructure.exception.ResourceAlreadyExistsException;
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
            throw new ResourceAlreadyExistsException("A patient has been registered with name " + patient.getFullName());
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
        Optional<PatientEntity> optionalPatientEntity = iPatientCrudRepository.findById(id);
        if (optionalPatientEntity.isPresent()) {
            return patientMapper.toPatient(optionalPatientEntity.get());
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

    @Override
    public void deleteById(Integer id) {
        Optional<PatientEntity> optionalPatientEntity = iPatientCrudRepository.findById(id);
        if (optionalPatientEntity.isPresent()) {
            PatientEntity patientEntity = optionalPatientEntity.get();
            patientEntity.setLogicallyRemoved(true);
            iPatientCrudRepository.save(patientEntity);
        } else {
            throw new ResourceNotFoundException("Patient with ID " + id + " not found");
        }
    }

    @Override
    public void update(Patient patient) {
        Optional<PatientEntity> optionalPatientEntity = iPatientCrudRepository.findById(patient.getId());
        if (optionalPatientEntity.isPresent()) {
            try {
                PatientEntity existingEntity = optionalPatientEntity.get();
                boolean hasChanges = !existingEntity.getFullName().equals(patient.getFullName()) ||
                                !existingEntity.getBirthday().equals(patient.getBirthday()) ||
                                !existingEntity.getAddress().equals(patient.getAddress()) ||
                                !existingEntity.getTelephone().equals(patient.getTelephone()) ||
                                !existingEntity.getEmail().equals(patient.getEmail());

                if (hasChanges) {
                    existingEntity.setFullName(patient.getFullName());
                    existingEntity.setBirthday(patient.getBirthday());
                    existingEntity.setAddress(patient.getAddress());
                    existingEntity.setTelephone(patient.getTelephone());
                    existingEntity.setEmail(patient.getEmail());

                    iPatientCrudRepository.save(existingEntity);
                } else {
                    throw new RuntimeException("No changes detected for patient with ID " + patient.getId());
                }

            } catch (Exception e) {
                throw new RuntimeException("Error updating patient with ID " + patient.getId() + ": " + e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException("Patient with ID " + patient.getId() + " not found");
        }
    }

}