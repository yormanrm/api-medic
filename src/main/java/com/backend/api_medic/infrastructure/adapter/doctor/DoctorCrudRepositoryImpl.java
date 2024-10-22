package com.backend.api_medic.infrastructure.adapter.doctor;

import com.backend.api_medic.domain.model.Doctor;
import com.backend.api_medic.domain.ports.IDoctorRepository;

public class DoctorCrudRepositoryImpl implements IDoctorRepository {
    @Override
    public Doctor save(Doctor doctor) {
        return null;
    }

    @Override
    public Iterable<Doctor> findAll() {
        return null;
    }

    @Override
    public Doctor findById(Integer id) {
        return null;
    }

    @Override
    public Iterable<Doctor> findBySomeTextfield(String textfield) {
        return null;
    }
}
