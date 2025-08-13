package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.Patient;

public interface IPatientRepository {
    Patient save(Patient patient);

    Iterable<Patient> findAll();

    Patient findById(Integer id);

    Iterable<Patient> searchByFullName(String name);

    void deleteById(Integer id);

    void update(Patient patient);
}