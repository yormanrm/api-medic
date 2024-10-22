package com.backend.api_medic.infrastructure.adapter.doctor;

import com.backend.api_medic.infrastructure.entity.DoctorEntity;
import org.springframework.data.repository.CrudRepository;

public interface IDoctorCrudRepository extends CrudRepository<DoctorEntity, Integer> {
}