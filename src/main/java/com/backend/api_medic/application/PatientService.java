package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.Patient;
import com.backend.api_medic.domain.ports.IPatientRepository;

public class PatientService {
    private final IPatientRepository iPatientRepository;

    public PatientService(IPatientRepository iPatientRepository) {
        this.iPatientRepository = iPatientRepository;
    }

    public Patient save(Patient patient) {
        return iPatientRepository.save(patient);
    }

    public Iterable<Patient> findAll() {
        return iPatientRepository.findAll();
    }

    public Patient findById(Integer id) {
        return iPatientRepository.findById(id);
    }

    public Iterable<Patient> searchByFullName(String name) {
        return iPatientRepository.searchByFullName(name);
    }
}