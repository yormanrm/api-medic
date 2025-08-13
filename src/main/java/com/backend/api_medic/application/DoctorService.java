package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.Doctor;
import com.backend.api_medic.domain.ports.IDoctorRepository;
import com.backend.api_medic.infrastructure.dto.response.NewEmployeeDTO;

public class DoctorService {
    private final IDoctorRepository iDoctorRepository;

    public DoctorService(IDoctorRepository iDoctorRepository) {
        this.iDoctorRepository = iDoctorRepository;
    }

    public NewEmployeeDTO save(Doctor doctor) {
        return iDoctorRepository.save(doctor);
    }

    public Iterable<Doctor> findAll() {
        return iDoctorRepository.findAll();
    }

    public Doctor findById(Integer id) {
        return iDoctorRepository.findById(id);
    }

    public Iterable<Doctor> searchBySomeTextfield(String textfield) {
        return iDoctorRepository.searchBySomeTextfield(textfield);
    }

    public void deleteById(Integer id) {
        iDoctorRepository.deleteById(id);
    }

    public void update(Doctor doctor) {
        iDoctorRepository.update(doctor);
    }
}