package com.backend.api_medic.application;

import com.backend.api_medic.domain.model.Doctor;
import com.backend.api_medic.domain.ports.IDoctorRepository;

public class DoctorService {
    private final IDoctorRepository iDoctorRepository;

    public DoctorService(IDoctorRepository iDoctorRepository) {
        this.iDoctorRepository = iDoctorRepository;
    }

    public Doctor save(Doctor doctor) {
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
}