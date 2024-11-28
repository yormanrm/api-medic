package com.backend.api_medic.domain.ports;

import com.backend.api_medic.domain.model.Doctor;
import com.backend.api_medic.infrastructure.dto.response.NewEmployeeDTO;

public interface IDoctorRepository {
    NewEmployeeDTO save(Doctor doctor);

    Iterable<Doctor> findAll();

    Doctor findById(Integer id);

    Iterable<Doctor> searchBySomeTextfield(String textfield);
//    void deleteById(Integer id);
}