package com.backend.api_medic.infrastructure.adapter.patient;

import com.backend.api_medic.domain.model.Patient;
import com.backend.api_medic.domain.ports.IPatientRepository;
import com.backend.api_medic.infrastructure.entity.PatientEntity;
import com.backend.api_medic.infrastructure.exception.PatientAlreadyExistsException;
import com.backend.api_medic.infrastructure.mapper.PatientMapper;
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
        return null;
    }

    @Override
    public Patient findById(Integer id) {
        return null;
    }

    @Override
    public Iterable<Patient> findByName(String name) {
        return null;
    }

}
