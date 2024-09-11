package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.Doctor;

public interface IDoctorRepository {
    Doctor save(Doctor doctor);

    Iterable<Doctor> findAll();

    Doctor findById(Integer id);

    Iterable<Doctor> findBySomeTextfield(String textfield);
//    void deleteById(Integer id);
}